package com.speedyteller.reporting.api.web.dto.response

import java.math.BigDecimal

data class ReportResponseDTO(val response: List<ReportInfo> = mutableListOf()) : ResponseDTO() {
    data class ReportInfo(var count: Long? = null, var total: BigDecimal? = null, var currency: String? = null)
}
