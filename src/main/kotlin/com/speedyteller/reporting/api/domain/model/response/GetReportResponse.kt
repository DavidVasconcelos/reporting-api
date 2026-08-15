package com.speedyteller.reporting.api.domain.model.response

import java.math.BigDecimal

data class GetReportResponse(val count: Long? = null, val total: BigDecimal? = null, val currency: String? = null)
