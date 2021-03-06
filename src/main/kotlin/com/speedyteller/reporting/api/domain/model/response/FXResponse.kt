package com.speedyteller.reporting.api.domain.model.response

import com.speedyteller.reporting.api.domain.model.FXTransaction
import java.math.BigDecimal

data class FXResponse(val merchant: FXMerchant)

data class FXMerchant(
    var originalAmount: BigDecimal? = null,
    var originalCurrency: String? = null
) {
    constructor(fxTransaction: FXTransaction) : this() {
        this.originalAmount = fxTransaction.originalAmount
        this.originalCurrency = fxTransaction.originalCurrency
    }
}
