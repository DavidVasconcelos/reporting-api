package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.Customer

interface CustomerService {

    fun getCustomer(transactionId: String) : Customer
}