package com.speedyteller.reporting.api.domain.model

import java.math.BigDecimal

data class Report(val count: Long? = null, val total: BigDecimal? = null, val currency: String? = null)
