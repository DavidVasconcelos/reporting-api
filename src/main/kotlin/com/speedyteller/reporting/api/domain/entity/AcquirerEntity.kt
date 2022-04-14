package com.speedyteller.reporting.api.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "acquirer")
data class AcquirerEntity(
    @Id
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "name")
    val name: String? = null,
    @Column(name = "code")
    val code: String? = null,
    @Column(name = "type")
    val type: String? = null
)
