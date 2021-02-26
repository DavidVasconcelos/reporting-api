package com.speedyteller.reporting.api.domain.model

import java.math.BigDecimal

data class FXTransaction(
    val id: Int? = null,
    val originalAmount : BigDecimal? = null,
    val originalCurrency : String? = null
)
