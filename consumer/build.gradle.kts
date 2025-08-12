plugins {
  kotlin("jvm") version "2.2.0"
  kotlin("plugin.spring") version "2.2.0"
  id("org.springframework.boot") version "4.0.0-M1"
  id("io.spring.dependency-management") version "1.1.7"
}

group = "com.fResult"
version = "0.0.1"

dependencies {
  implementation(project(":producer"))

  // TODO: Remove the specific version `5.0.0-M1` when Spring Boot 4.0.0 is released
  implementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner:5.0.0-M1")
}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
