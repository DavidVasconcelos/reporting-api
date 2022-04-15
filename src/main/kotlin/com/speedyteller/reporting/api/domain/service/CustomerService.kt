package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.domain.usecase.FindCustomerById
import com.speedyteller.reporting.api.domain.usecase.FindTransactionById
import com.speedyteller.reporting.api.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val findTransactionById: FindTransactionById,
    val findCustomerById: FindCustomerById
) {

    fun getCustomer(transactionId: String): GetCustomerResponse {
        val transaction = findTransactionById.handle(transactionId = transactionId)
        transaction.customerId?.let { customerId ->
            val customer = findCustomerById.handle(customerId = customerId)
            return GetCustomerResponse(customerInfo = customer)
        } ?: throw NotFoundException(CUSTOMER_ID_NOT_PRESSENT_ERROR_MESSAGE)
    }

    companion object {
        const val CUSTOMER_ID_NOT_PRESSENT_ERROR_MESSAGE = "Customer id not present on transaction"
    }
}
