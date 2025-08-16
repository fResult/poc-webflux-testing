package com.fresult.consumer

import com.fresult.producer.CustomerResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.test.annotation.DirtiesContext
import reactor.test.StepVerifier
import kotlin.test.Test

@DirtiesContext
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = [ConsumerApplication::class],
)
@AutoConfigureStubRunner(
  ids = [StubRunnerCustomerClientTest.PRODUCER_ARTIFACT_ID],
  stubsMode = StubRunnerProperties.StubsMode.LOCAL,
)
class StubRunnerCustomerClientTest {
  companion object {
    const val PRODUCER_ARTIFACT_ID = "com.fresult:producer"
  }

  @Autowired
  private lateinit var customerClient: CustomerClient

  @StubRunnerPort(PRODUCER_ARTIFACT_ID)
  private val producerServicePort = 0

  @Test
  fun `get all customers`() {
    val base = "localhost:$producerServicePort"
    customerClient.base = base
    val customers = customerClient.getCustomers()

    StepVerifier.create(customers)
      .expectNext(CustomerResponse("1", "John Wick"))
      .expectNext(CustomerResponse("2", "Thomas Anderson"))
      .verifyComplete()
  }
}
