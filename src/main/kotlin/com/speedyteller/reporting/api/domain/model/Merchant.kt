package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.MerchantEntity

data class Merchant(

    var id: Long? = null,
    var name: String? = null
) {
    constructor(entity: MerchantEntity) : this() {
        this.id = entity.id
        this.name = entity.name
    }
}
