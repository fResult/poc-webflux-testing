package com.fresult.producer

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface CustomerRepository : ReactiveMongoRepository<Customer, String> {
  fun findByName(string: String): Flux<Customer>
}
