package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.domain.service.CustomerService
import com.speedyteller.reporting.api.domain.usecase.FindCustomerByTransactionId
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(val findCustomerByTransactionId: FindCustomerByTransactionId) : CustomerService {

    @Cacheable(cacheNames = ["customers"], key = "#transactionId")
    override fun getCustomer(transactionId: String): GetCustomerResponse {
        val customer = findCustomerByTransactionId.handle(transactionId = transactionId)
        return GetCustomerResponse(customerInfo = customer)
    }
}
