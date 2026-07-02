package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.domain.service.CustomerService
import com.speedyteller.reporting.api.domain.usecase.FindCustomerByTransactionId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(val findCustomerByTransactionId: FindCustomerByTransactionId) : CustomerService {

    @Cacheable(cacheNames = ["customers"], key = "#transactionId")
    override fun getCustomer(transactionId: String): GetCustomerResponse {
        logger.info("Getting customer with transactionId: $transactionId")
        val customer = findCustomerByTransactionId.handle(transactionId = transactionId)
        return GetCustomerResponse(customerInfo = customer)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
