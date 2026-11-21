# Build stage
FROM eclipse-temurin:25-jdk-alpine AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
COPY mvnw pom.xml $HOME/
COPY .mvn $HOME/.mvn
RUN ./mvnw -B dependency:go-offline --fail-never
COPY src $HOME/src
RUN ./mvnw -B package -DskipTests

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
