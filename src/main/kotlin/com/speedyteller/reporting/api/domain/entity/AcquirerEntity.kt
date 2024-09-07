package com.speedyteller.reporting.api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "acquirer")
data class AcquirerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acquirer_id_seq")
    @SequenceGenerator(name = "acquirer_id_seq", sequenceName = "acquirer_id_seq", allocationSize = 1)
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "name")
    val name: String? = null,
    @Column(name = "code")
    val code: String? = null,
    @Column(name = "type")
    val type: String? = null
)
