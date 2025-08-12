package com.fresult.consumer

import com.fresult.producer.Customer
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import reactor.test.StepVerifier

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class WiremockCustomerClientTest {
  @Autowired
  private lateinit var customerClient: CustomerClient

  @Autowired
  private lateinit var environment: Environment

  @BeforeEach
  fun setupWireMock() {
    val wiremockServerPort = environment.getProperty("wiremock.server.port", Int::class.java) ?: 0
    val base = "localhost:$wiremockServerPort"
    customerClient.base = base

    val json = """
      [
        {"id": "1", "name": "John Wick"},
        {"id": "2", "name": "Thomas Anderson"}
      ]
    """.trimIndent()

    WireMock.stubFor(
      WireMock.get("/customers")
        .willReturn(
          WireMock.aResponse()
            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .withBody(json)
        )
    )
  }

  @Test
  fun `get all customers`() {
    val customers = customerClient.getCustomers()

    StepVerifier.create(customers)
      .expectNext(Customer("1", "John Wick"))
      .expectNext(Customer("2", "Thomas Anderson"))
      .verifyComplete()
  }
}
