package com.speedyteller.reporting.api.domain.model.response

import com.speedyteller.reporting.api.domain.model.FXTransaction
import java.io.Serial
import java.io.Serializable
import java.math.BigDecimal

data class FXResponse(val merchant: FXMerchant) : Serializable {
    companion object {
        @Serial
        private const val serialVersionUID: Long = -2978247445383129465L
    }
}

data class FXMerchant(var originalAmount: BigDecimal? = null, var originalCurrency: String? = null) : Serializable {
    constructor(fxTransaction: FXTransaction) : this() {
        this.originalAmount = fxTransaction.originalAmount
        this.originalCurrency = fxTransaction.originalCurrency
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = -6308528030740206995L
    }
}
