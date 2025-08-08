package com.fresult.producer

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DataMongoTest
class CustomerRepositoryTest {
  companion object {
    @Container
    val mongoDbContainer: MongoDBContainer = MongoDBContainer("mongo:8.0.12")

    @DynamicPropertySource
    fun setProperties(registry: DynamicPropertyRegistry): Unit =
      registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl)
  }
}
