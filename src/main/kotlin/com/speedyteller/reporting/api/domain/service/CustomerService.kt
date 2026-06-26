package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.domain.usecase.FindCustomerByTransactionId
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CustomerService(val findCustomerByTransactionId: FindCustomerByTransactionId) {

    @Cacheable(cacheNames = ["customers"], key = "#transactionId")
    fun getCustomer(transactionId: String): GetCustomerResponse {
        val customer = findCustomerByTransactionId.handle(transactionId = transactionId)
        return GetCustomerResponse(customerInfo = customer)
    }
}
