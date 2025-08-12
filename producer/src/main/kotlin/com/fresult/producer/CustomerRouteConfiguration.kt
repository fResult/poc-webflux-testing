package com.fresult.producer

import kotlinx.coroutines.reactive.asFlow
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CustomerRouteConfiguration(private val repository: CustomerRepository) {
  @Bean
  fun customerRoutes(): RouterFunction<ServerResponse> = coRouter {
    GET("/customers") { req ->
      val customers = repository.findAll()
      ServerResponse.ok().bodyAndAwait(customers.asFlow())
    }
  }
}
