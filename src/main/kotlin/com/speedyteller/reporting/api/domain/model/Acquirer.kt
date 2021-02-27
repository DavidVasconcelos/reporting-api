package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.AcquirerEntity

data class Acquirer(

    var id: Long? = null,
    var name: String? = null,
    var code: Long? = null
) {
    constructor(entity: AcquirerEntity) : this() {
        this.id = entity.id
        this.name = entity.name
        this.code = entity.code
    }
}
