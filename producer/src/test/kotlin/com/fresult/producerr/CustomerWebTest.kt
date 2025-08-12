package com.fresult.producerr

import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux

@WebFluxTest
@Import(CustomerRouteConfiguration::class)
class CustomerWebTest {
  @Autowired
  private lateinit var webTestClient: WebTestClient

  @MockitoBean
  private lateinit var customerRepository: CustomerRepository

  @Test
  fun `get all customers`() {
    val mockResult = Flux.just(Customer("1", "John"), Customer("2", "Smith"))

    given(customerRepository.findAll()).willReturn(mockResult)

    webTestClient.get()
      .uri("/customers")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isOk
      .expectHeader()
      .contentType(MediaType.APPLICATION_JSON)
      .expectBodyList(Customer::class.java)
      .hasSize(2)
      .contains(Customer("1", "John"), Customer("2", "Smith"))
  }
}
