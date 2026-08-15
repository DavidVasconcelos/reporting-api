package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.MerchantEntity
import java.io.Serial
import java.io.Serializable

data class Merchant(var id: Long? = null, var name: String? = null) : Serializable {
    constructor(entity: MerchantEntity) : this() {
        this.id = entity.id
        this.name = entity.name
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = -1243947401367750328L
    }
}
