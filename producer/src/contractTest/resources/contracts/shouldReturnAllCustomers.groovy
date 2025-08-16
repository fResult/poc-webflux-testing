package contracts

import org.springframework.cloud.contract.spec.Contract

import static org.springframework.cloud.contract.spec.internal.HttpMethods.GET
import static org.springframework.cloud.contract.spec.internal.HttpStatus.OK
import static org.springframework.cloud.contract.spec.internal.MediaTypes.APPLICATION_JSON

Contract.make {
  request {
    method GET
    url "/customers"
  }

  response {
    status OK
    headers {
      contentType APPLICATION_JSON
    }
    body([
        [id: 1, name: "John Wick"],
        [id: 2, name: "Thomas Anderson"],
    ])
  }
}