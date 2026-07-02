package com.speedyteller.reporting.api.domain.model.response

import com.speedyteller.reporting.api.domain.model.Customer
import java.io.Serial
import java.io.Serializable

data class GetCustomerResponse(val customerInfo: Customer) : Serializable {
    companion object {
        @Serial
        private const val serialVersionUID: Long = 1763691344165220524L
    }
}
