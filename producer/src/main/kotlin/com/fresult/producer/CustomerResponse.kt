package com.fresult.producer

data class CustomerResponse(val id: String, val name: String) {
  companion object {
    fun fromDAO(dao: Customer): CustomerResponse =
      CustomerResponse(dao.id, dao.name)
  }
}
