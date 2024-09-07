package com.speedyteller.reporting.api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "agent_info")
data class AgentInfoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agent_info_id_seq")
    @SequenceGenerator(name = "agent_info_id_seq", sequenceName = "agent_info_id_seq", allocationSize = 1)
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "customer_ip")
    val customerIp: String? = null,
    @Column(name = "customer_user_agent")
    val customerUserAgent: String? = null,
    @Column(name = "merchant_ip")
    val merchantIp: String? = null
)
