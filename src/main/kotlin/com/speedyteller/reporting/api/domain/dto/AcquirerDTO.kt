package com.speedyteller.reporting.api.domain.dto

import com.speedyteller.reporting.api.domain.model.Acquirer

data class AcquirerDTO(
    var id: Long? = null,
    var name: String? = null,
    var code: String? = null,
    var type: String? = null
) {
    constructor(model: Acquirer) : this() {
        this.id = model.id
        this.name = model.name
        this.code = model.code
        this.type = model.type
    }
}
