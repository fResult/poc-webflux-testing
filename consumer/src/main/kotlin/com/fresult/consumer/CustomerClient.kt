package com.fresult.consumer

import com.fresult.producer.Customer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class CustomerClient {
  companion object {
    private val log: Logger = LogManager.getLogger(CustomerClient::class.java)
  }

  var base: String = ""
    set(value) {
      field = value
      log.info("Base URL set to: $field")
    }

  fun getCustomers(): Flux<Customer> {
    TODO("Not yet implemented")
  }
}
