package com.speedyteller.reporting.api.web.dto.response

import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.web.dto.CustomerDTO

data class GetCustomerResponseDTO(val customerInfo: CustomerDTO) {
    constructor(model: GetCustomerResponse) : this(customerInfo = CustomerDTO(model = model.customerInfo))
}
