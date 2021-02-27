package com.speedyteller.reporting.api.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "merchant")
data class Merchant(

    @Id
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "name")
    val name: String? = null
)
