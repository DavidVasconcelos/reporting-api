package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse

interface CustomerService {

    fun getCustomer(transactionId: String): GetCustomerResponse
}
