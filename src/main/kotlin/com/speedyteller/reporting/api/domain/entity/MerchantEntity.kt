package com.speedyteller.reporting.api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "merchant")
data class MerchantEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "merchant_id_seq")
    @SequenceGenerator(name = "merchant_id_seq", sequenceName = "merchant_id_seq", allocationSize = 1)
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "name")
    val name: String? = null
)
