package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.domain.usecase.FindCustomerByTranscationId
import org.springframework.stereotype.Service

@Service
class CustomerService(val findCustomerByTranscationId: FindCustomerByTranscationId) {

    fun getCustomer(transactionId: String): GetCustomerResponse {
        val customer = findCustomerByTranscationId.handle(transactionId = transactionId)
        return GetCustomerResponse(customerInfo = customer)
    }
}
