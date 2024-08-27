package com.speedyteller.reporting.api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

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
