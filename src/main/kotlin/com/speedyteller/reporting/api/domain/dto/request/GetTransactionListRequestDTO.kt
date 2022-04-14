package com.speedyteller.reporting.api.domain.dto.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class GetTransactionListRequestDTO(
    var fromDate: String? = null,
    var toDate: String? = null,
    var status: String? = null,
    var operation: String? = null,
    var paymentMethod: String? = null,
    var errorCode: String? = null,
    var filterField: String? = null,
    var filterValue: String? = null,
    var merchantId: Int? = null,
    var acquirerId: Int? = null,
)
