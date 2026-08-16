package com.speedyteller.reporting.api.web.dto.response

import com.speedyteller.reporting.api.domain.model.Report
import java.math.BigDecimal

data class GetReportResponseDTO(val response: List<GetReportDTO> = mutableListOf()) : ResponseDTO()

data class GetReportDTO(var count: Long? = null, var total: BigDecimal? = null, var currency: String? = null) {
    constructor(model: Report) : this() {
        this.count = model.count
        this.total = model.total
        this.currency = model.currency
    }
}
