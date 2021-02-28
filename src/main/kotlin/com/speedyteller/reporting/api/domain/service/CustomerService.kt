package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.GetCustomerResponse

interface CustomerService {

    fun getCustomer(transactionId: String): GetCustomerResponse
}
