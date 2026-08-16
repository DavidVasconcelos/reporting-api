package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.Customer

fun interface CustomerService {

    fun getCustomer(transactionId: String): Customer
}
