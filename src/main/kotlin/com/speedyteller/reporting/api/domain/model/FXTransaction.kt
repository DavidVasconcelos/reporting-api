package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.FXTransactionEntity
import java.math.BigDecimal

data class FXTransaction(

    var id: Long? = null,
    var originalAmount: BigDecimal? = null,
    var originalCurrency: String? = null
) {
    constructor(entity: FXTransactionEntity) : this() {
        this.id = entity.id
        this.originalAmount = entity.originalAmount
        this.originalCurrency = entity.originalCurrency
    }
}

