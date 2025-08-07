package com.fresult.producer

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CustomerTest {

  @Test
  fun create() {
    val id = "12345"
    val name = "foo"

    val customer = Customer(id, name)

    assertEquals(id, customer.id)
    assertThat(customer.id, Matchers.`is`(id))
    assertEquals(name, customer.name)
  }
}
