package com.fresult.producer

import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CustomerRouteConfiguration(private val repository: CustomerRepository) {
  @Bean
  fun customerRoutes(): RouterFunction<ServerResponse> = coRouter {
    GET("/customers", ::all)
  }

  private suspend fun all(request: ServerRequest): ServerResponse =
    repository.findAll()
      .map(CustomerResponse::fromDAO)
      .collectList()
      .flatMap(ServerResponse.ok()::bodyValue)
      .awaitSingle()
}
