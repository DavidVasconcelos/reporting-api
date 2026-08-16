package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.service.CustomerService
import com.speedyteller.reporting.api.domain.usecase.FindCustomerByTransactionId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(val findCustomerByTransactionId: FindCustomerByTransactionId) : CustomerService {

    @Cacheable(value = ["customers"], keyGenerator = "customKeyGenerator")
    override fun getCustomer(transactionId: String): Customer {
        logger.info("Getting customer with transactionId: $transactionId")
        return findCustomerByTransactionId.handle(transactionId = transactionId)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
