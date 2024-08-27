package com.speedyteller.reporting.api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "agent_info")
data class AgentInfoEntity(
    @Id
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "customer_ip")
    val customerIp: String? = null,
    @Column(name = "customer_user_agent")
    val customerUserAgent: String? = null,
    @Column(name = "merchant_ip")
    val merchantIp: String? = null
)
