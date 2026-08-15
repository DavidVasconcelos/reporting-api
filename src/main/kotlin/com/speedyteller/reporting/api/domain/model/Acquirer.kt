package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.AcquirerEntity
import java.io.Serial
import java.io.Serializable

data class Acquirer(
    var id: Long? = null,
    var name: String? = null,
    var code: String? = null,
    var type: String? = null,
) : Serializable {

    constructor(entity: AcquirerEntity) : this() {
        this.id = entity.id
        this.name = entity.name
        this.code = entity.code
        this.type = entity.type
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = 3083820620584375348L
    }
}
