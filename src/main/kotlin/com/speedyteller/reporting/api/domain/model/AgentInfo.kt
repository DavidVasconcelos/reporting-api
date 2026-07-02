package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.AgentInfoEntity
import java.io.Serial
import java.io.Serializable

data class AgentInfo(
    var id: Long? = null,
    var customerIp: String? = null,
    var customerUserAgent: String? = null,
    var merchantIp: String? = null,
) : Serializable {
    constructor(entity: AgentInfoEntity) : this() {
        this.id = entity.id
        this.customerIp = entity.customerIp
        this.customerUserAgent = entity.customerUserAgent
        this.merchantIp = entity.merchantIp
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = 5085694584168465559L
    }
}
