package com.speedyteller.reporting.api.web.dto

data class AgentInfoDTO(
    var id: Long? = null,
    var customerIp: String? = null,
    var customerUserAgent: String? = null,
    var merchantIp: String? = null,
)
