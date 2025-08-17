# POC HTTP Testing

This repository is a parallel project to [Learning Webflux 3.0 (module
`kotlin.08-testing`](https://github.com/fResult/Learn-Spring-Webflux-3.0/tree/main/kotlin/08-testing) and is used for
learning R2DBC with Spring WebFlux.

## Decision Record of the Project

At first, I start this repository with Spring Boot 4.0.0-M1, but there are some compatibility issues with the Rest Assured.\
Then, I decided to use Spring Boot 3.5.4, which is compatible with the Rest Assured 5.5.5.

## Prerequisites

- JDK 24
- Docker (used for running test with the PostgreSQL database in a TestContainers)

## Available Scripts

Before building project, we need to start Docker Daemon first.\
Otherwise, we can use `./gradlew clean bootJar` instead of `./gradlew clean build`

### Build

Build and publish the artifacts (with stubs) to the local Maven repository:

```bash
./gradlew :producer:clean :producer:bootJar :producer:publishToMavenLocal
```

Verify the build by checking the stubs of the Maven repository:

```console
➜ ls -l ~/.m2/repository/com/fresult/producer/0.0.1
total 64
-rw-r--r--@ 1 fresult  staff  14351 Aug 16 20:01 producer-0.0.1-plain.jar
-rw-r--r--@ 1 fresult  staff   1654 Aug 16 20:01 producer-0.0.1-stubs.jar
-rw-r--r--@ 1 fresult  staff   3423 Aug 16 20:01 producer-0.0.1.module
-rw-r--r--@ 1 fresult  staff   6866 Aug 16 20:01 producer-0.0.1.pom
```

Build `consumer` module

```bash
./gradlew :consumer:clean :consumer:build
./gradlew :consumer:test --tests "com.fresult.consumer.StubRunnerCustomerClientTest"
```
