package com.fresult.consumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class CustomerClientConfiguration {
  @Bean("webCustomerClient")
  fun webCustomerClient(): WebClient {
    return WebClient.builder().build()
  }
}
