package com.fresult.consumer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.core.env.Environment

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class WiremockCustomerClientTest {
  @Autowired
  private lateinit var customerClient: CustomerClient

  @Autowired
  private lateinit var environment: Environment
}
