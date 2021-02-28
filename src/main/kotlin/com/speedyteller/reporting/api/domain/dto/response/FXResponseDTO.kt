package com.speedyteller.reporting.api.domain.dto.response

import com.speedyteller.reporting.api.domain.model.response.FXMerchant
import com.speedyteller.reporting.api.domain.model.response.FXResponse
import java.math.BigDecimal

data class FXResponseDTO(val merchant: FXMerchantDTO) {
    constructor(model: FXResponse) : this(merchant = FXMerchantDTO(model.merchant))
}

data class FXMerchantDTO(
    var originalAmount: BigDecimal? = null,
    var originalCurrency: String? = null
) {
    constructor(model: FXMerchant) : this() {
        this.originalAmount = model.originalAmount
        this.originalCurrency = model.originalCurrency
    }
}
