package com.speedyteller.reporting.api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "merchant")
data class MerchantEntity(
    @Id
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "name")
    val name: String? = null
)
