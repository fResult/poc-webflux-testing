# POC HTTP Testing

This repository is a parallel project to [Learning Webflux 3.0 (module
`kotlin.08-testing`](https://github.com/fResult/Learn-Spring-Webflux-3.0/tree/main/kotlin/08-testing) and is used for
learning R2DBC with Spring WebFlux.

## Prerequisites

- JDK 24
- Docker (used for running test with the PostgreSQL database in a TestContainers)

## Available Scripts

Before building project, we need to start Docker Daemon first.\
Otherwise, we can use `./gradlew clean bootJar` instead of `./gradlew clean build`

### Build

```bash
./gradlew clean build \
  :producer:clean :producer:build \
  :consumer:clean :consumer:build
```
