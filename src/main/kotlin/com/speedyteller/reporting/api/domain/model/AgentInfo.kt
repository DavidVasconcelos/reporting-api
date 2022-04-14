package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.AgentInfoEntity

data class AgentInfo(
    var id: Long? = null,
    var customerIp: String? = null,
    var customerUserAgent: String? = null,
    var merchantIp: String? = null
) {
    constructor(entity: AgentInfoEntity) : this() {
        this.id = entity.id
        this.customerIp = entity.customerIp
        this.customerUserAgent = entity.customerUserAgent
        this.merchantIp = entity.merchantIp
    }
}
