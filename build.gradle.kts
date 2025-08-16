plugins {
  kotlin("jvm") version "2.2.0"
  kotlin("plugin.spring") version "2.2.0"
//  id("org.springframework.boot") version "4.0.0-M1"
  id("org.springframework.boot") version "3.5.4"
  id("io.spring.dependency-management") version "1.1.7"
}

val springCloudVersion = "2025.0.0"

allprojects {
  group = "com.fResult"
  version = "0.0.1"

  repositories {
    mavenCentral()
    // Remove if Spring Boot 4.0.0 is released
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
  }
}

tasks.named("bootJar") {
  enabled = false
}

tasks.named("jar") {
  enabled = true
}

subprojects {
  apply(plugin = "kotlin")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")

  dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    dependencyManagement {
      imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
      }
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}
