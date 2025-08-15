package contracts

import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.cloud.contract.spec.internal.HttpStatus

Contract.make {
  request {
    method(HttpMethods.GET)
    url("/customers")
  }
  response {
    body """
    [
      {"id": 1, "name": "John Wick"},
      {"id": 2, "name": "Thomas Anderson"}
    ]
    """.trimIndent()
    status(HttpStatus.OK)
    headers {
      contentType(applicationJson())
    }
  }
}
