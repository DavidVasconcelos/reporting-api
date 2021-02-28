package com.speedyteller.reporting.api.domain.dto

import com.speedyteller.reporting.api.domain.model.FXTransaction
import java.math.BigDecimal

data class FXTransactionDTO(

    var id: Long? = null,
    var originalAmount: BigDecimal? = null,
    var originalCurrency: String? = null
) {
    constructor(model: FXTransaction) : this() {
        this.id = model.id
        this.originalAmount = model.originalAmount
        this.originalCurrency = model.originalCurrency
    }
}
