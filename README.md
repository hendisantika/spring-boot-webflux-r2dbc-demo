# Spring Boot Reactive Rest API Example with R2DBC and WebFlux

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-webflux-r2dbc-demo/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-webflux-r2dbc-demo/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?logo=springboot)

This project demonstrates a reactive Spring Boot application using **R2DBC** (Reactive Relational Database Connectivity)
for non-blocking database access and **Spring WebFlux** for building reactive, high-performance web services.

## Technologies

- **Java 25** (LTS)
- **Spring Boot 4.1**: Simplifies setup and configuration for production-ready applications.
- **Spring WebFlux**: Fully non-blocking, reactive framework for building REST APIs.
- **Spring Data R2DBC** + **r2dbc-pool**: Asynchronous, reactive database operations with relational databases.
- **MySQL** via `io.asyncer:r2dbc-mysql`
- **MapStruct** for DTO ↔ entity mapping, **Lombok** to reduce boilerplate
- **Spring Boot Docker Compose** support to start MySQL automatically

## Prerequisites

- **JDK 25** or later
- **Maven 3.9+** (or use the bundled Maven Wrapper `./mvnw`)
- **Docker** (for MySQL via `compose.yaml`)

## Getting Started

1. Clone the repository:
   ```shell
   git clone https://github.com/hendisantika/spring-boot-webflux-r2dbc-demo.git
   cd spring-boot-webflux-r2dbc-demo
   ```
2. Run the application (Spring Boot will start the MySQL container from `compose.yaml` automatically):
   ```shell
   ./mvnw clean spring-boot:run
   ```
3. The application is available at `http://localhost:8080`.

### Build & Test

```shell
./mvnw clean package
```

### Run with Docker

```shell
docker compose up -d                       # start MySQL
docker build -t webflux-r2dbc .            # build image (JDK 25 / Temurin)
docker run --rm -p 8080:8080 --network host webflux-r2dbc
```

## API Endpoints

CRUD operations are exposed by the annotated `ProductController` under `/api/v2`:

| Method | Endpoint                 | Description          |
|--------|--------------------------|----------------------|
| GET    | `/api/v2/product`        | List all products    |
| GET    | `/api/v2/product/{id}`   | Get product by id    |
| POST   | `/api/v2/product`        | Create a new product |
| PUT    | `/api/v2/product/{id}`   | Update a product     |
| DELETE | `/api/v2/product/{id}`   | Delete a product     |

Sample request:

```shell
curl -X POST http://localhost:8080/api/v2/product \
  -H "Content-Type: application/json" \
  -d '{"productName":"Spite","productType":"Beverage","price":10.0,"quantity":10}'
```

## CI/CD & Dependency Management

- **GitHub Actions** (`.github/workflows/maven.yml`) builds and tests the project on JDK 25 (Temurin) for every push and
  pull request to `main`, then submits the dependency graph.
- **Dependabot** (`.github/dependabot.yml`) keeps dependencies up to date:
    - Maven dependencies daily, grouped (Spring, R2DBC/database, MapStruct/Lombok, Maven plugins)
    - GitHub Actions, Docker base images and `compose.yaml` services weekly
    - Docker images are pinned to the JDK 25 line (major bumps ignored)
