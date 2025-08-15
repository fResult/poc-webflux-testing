package com.fresult.producer

import io.restassured.module.webtestclient.RestAssuredWebTestClient
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.junit.jupiter.api.BeforeEach
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.test.LocalServerPort
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.web.reactive.function.server.RouterFunction
import reactor.core.publisher.Flux

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  properties = ["server.port=0"],
)
class BaseClass {
  companion object {
    val log: Logger = LogManager.getLogger(BaseClass::class.java)

    @Configuration
    @Import(ProducerApplication::class)
    class TestConfiguration
  }

  @LocalServerPort
  private var port = 0

  @MockitoBean
  private lateinit var customerRepository: CustomerRepository

  @Autowired
  private lateinit var routerFunction: RouterFunction<*>

  @BeforeEach
  fun setUp() {
    log.info("The embedded test server is available on port: $port")

    val expectedCustomers = Flux.just(
      Customer("1", "John Wick"),
      Customer("2", "Thomas Anderson"),
    )

    given(customerRepository.findAll()).willReturn(expectedCustomers)

    RestAssuredWebTestClient.standaloneSetup(routerFunction)
  }
}
