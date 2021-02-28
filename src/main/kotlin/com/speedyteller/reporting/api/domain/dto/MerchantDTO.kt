package com.speedyteller.reporting.api.domain.dto

import com.speedyteller.reporting.api.domain.model.Merchant

data class MerchantDTO(

    var id: Long? = null,
    var name: String? = null
) {
    constructor(model: Merchant) : this() {
        this.id = model.id
        this.name = model.name
    }
}
