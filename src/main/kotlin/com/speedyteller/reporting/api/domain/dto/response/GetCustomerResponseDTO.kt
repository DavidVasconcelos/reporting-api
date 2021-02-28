package com.speedyteller.reporting.api.domain.dto.response

import com.speedyteller.reporting.api.domain.dto.CustomerDTO
import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse

data class GetCustomerResponseDTO(val customerInfo: CustomerDTO) {
    constructor(model: GetCustomerResponse) : this(customerInfo = CustomerDTO(model = model.customerInfo))
}
