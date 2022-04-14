package com.speedyteller.reporting.api.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "merchant_user")
data class UserEntity(

    @Id
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "email")
    val email: String? = null,

    @Column(name = "password")
    val password: String? = null
)
