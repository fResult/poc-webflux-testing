package com.fresult.producer

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

@Testcontainers
@DataMongoTest
class CustomerRepositoryTest {
  @Autowired
  lateinit var customerRepository: CustomerRepository

  companion object {
    @Container
    val mongoDbContainer: MongoDBContainer = MongoDBContainer("mongo:8.0.12")

    @DynamicPropertySource
    fun setProperties(registry: DynamicPropertyRegistry): Unit =
      registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl)
  }


  @Test
  fun `find by name`() {
    val commonName = "Jane"
    val customer1 = Customer("1", commonName)
    val customer2 = Customer("2", "John")
    val customer3 = Customer("3", commonName)

    val setup = customerRepository.deleteAll()
      .thenMany(
        customerRepository.saveAll(Flux.just(customer1, customer2, customer3))
      ).thenMany(
        customerRepository.findByName(commonName)
      )

    val isCommonName: (Customer) -> Boolean = { customer -> customer.name.equals(commonName, true) }

    StepVerifier.create(setup)
      .expectNextMatches(isCommonName)
      .expectNextMatches(isCommonName)
      .verifyComplete()
  }
}
