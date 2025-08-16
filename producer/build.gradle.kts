import kotlin.contracts.contract

plugins {
  kotlin("jvm") version "2.2.0"
  kotlin("plugin.spring") version "2.2.0"
  id("org.springframework.boot") version "4.0.0-M1"
  id("io.spring.dependency-management") version "1.1.7"
  // TODO: Remove the specific version `-M1` from `5.0.0-M1` when Spring Boot 4.0.0 is released
  id("org.springframework.cloud.contract") version "5.0.0-M1"
}

group = "com.fresult"
version = "0.0.1"

val springWebTestClientVersion = "5.5.6"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  // TODO: Remove the specific version `5.0.0-M1` when Spring Boot 4.0.0 is released
  testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier:5.0.0-M1")
  testImplementation("org.testcontainers:junit-jupiter")
  testImplementation("org.testcontainers:mongodb")
  testImplementation("io.rest-assured:spring-web-test-client")
}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
  environment("TESTCONTAINERS_RYUK_DISABLED", "true") // Disable Ryuk container for tests
}

contracts {
  baseClassForTests ="com.fresult.producer.BaseClass"
  testFramework = org.springframework.cloud.contract.verifier.config.TestFramework.JUNIT5
  testMode = org.springframework.cloud.contract.verifier.config.TestMode.WEBTESTCLIENT
  contractsDslDir = file("${project.projectDir}/src/contractTest/resources/contracts")
}
