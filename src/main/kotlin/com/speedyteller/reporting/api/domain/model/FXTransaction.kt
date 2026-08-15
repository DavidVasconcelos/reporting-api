package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.FXTransactionEntity
import java.io.Serial
import java.io.Serializable
import java.math.BigDecimal

data class FXTransaction(
    var id: Long? = null,
    var originalAmount: BigDecimal? = null,
    var originalCurrency: String? = null,
) : Serializable {
    constructor(entity: FXTransactionEntity) : this() {
        this.id = entity.id
        this.originalAmount = entity.originalAmount
        this.originalCurrency = entity.originalCurrency
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = 5813629785926827631L
    }
}
