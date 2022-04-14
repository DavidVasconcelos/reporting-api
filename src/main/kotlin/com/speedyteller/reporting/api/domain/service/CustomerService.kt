package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.port.PostgresPort
import org.springframework.stereotype.Service

@Service
class CustomerService(val postgresPort: PostgresPort) {

    fun getCustomer(transactionId: String): GetCustomerResponse {
        val transaction = postgresPort.findTransactionByTransactionId(transactionId = transactionId)
        transaction.customerId?.let { id ->
            val customer = postgresPort.findCustomerById(id = id)
            return GetCustomerResponse(customerInfo = customer)
        } ?: throw NotFoundException(CUSTOMER_ID_NOT_PRESSENT_ERROR_MESSAGE)
    }

    companion object {
        const val CUSTOMER_ID_NOT_PRESSENT_ERROR_MESSAGE = "Customer id not present on transaction"
    }
}
