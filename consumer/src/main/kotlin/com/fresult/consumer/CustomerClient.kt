package com.fresult.consumer

import com.fresult.producer.Customer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Component
class CustomerClient(@Qualifier("webCustomerClient") private val client: WebClient) {
  companion object {
    private val log: Logger = LogManager.getLogger(CustomerClient::class.java)
  }

  var base: String = ""
    set(value) {
      field = value
      log.info("Base URL set to: $field")
    }

  fun getCustomers(): Flux<Customer> {
    return client.get()
      .uri("http://$base/customers")
      .retrieve()
      .bodyToFlux(Customer::class.java)
  }
}
