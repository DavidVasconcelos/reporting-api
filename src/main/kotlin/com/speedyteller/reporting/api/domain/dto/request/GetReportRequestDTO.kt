package com.speedyteller.reporting.api.domain.dto.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class GetReportRequestDTO(
    var fromDate: String? = null,
    var toDate: String? = null,
    var merchant : Int? = null,
    var acquirer: Int? = null,
)