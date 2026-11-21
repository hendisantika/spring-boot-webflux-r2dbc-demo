# Build stage
FROM maven:3.9-eclipse-temurin-25-alpine AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
COPY pom.xml $HOME
RUN mvn -B dependency:go-offline --fail-never
COPY . $HOME
RUN mvn -B package -DskipTests

# Extract layers
FROM eclipse-temurin:25-jre AS builder
WORKDIR /application
COPY --from=build /usr/app/target/webflux-r2dbc-0.0.1-SNAPSHOT.jar application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --launcher --destination extracted

# Runtime stage
FROM eclipse-temurin:25-jre
WORKDIR /application
COPY --from=builder /application/extracted/dependencies/ ./
COPY --from=builder /application/extracted/spring-boot-loader/ ./
COPY --from=builder /application/extracted/snapshot-dependencies/ ./
COPY --from=builder /application/extracted/application/ ./
EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
