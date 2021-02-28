package com.speedyteller.reporting.api.domain.dto

import com.speedyteller.reporting.api.domain.model.GetCustomerResponse

data class GetCustomerResponseDTO(val customerInfo: CustomerDTO) {
    constructor(model: GetCustomerResponse) : this(customerInfo = CustomerDTO(model = model.customerInfo))
}
