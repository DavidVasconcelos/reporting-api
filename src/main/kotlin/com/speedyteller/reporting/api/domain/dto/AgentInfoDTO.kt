package com.speedyteller.reporting.api.domain.dto

import com.speedyteller.reporting.api.domain.model.AgentInfo

data class AgentInfoDTO(
    var id: Long? = null,
    var customerIp: String? = null,
    var customerUserAgent: String? = null,
    var merchantIp: String? = null
) {
    constructor(model: AgentInfo) : this() {
        this.id = model.id
        this.customerIp = model.customerIp
        this.customerUserAgent = model.customerUserAgent
        this.merchantIp = model.merchantIp
    }
}
