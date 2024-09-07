package com.speedyteller.reporting.api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "merchant_user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "merchant_user_id_seq")
    @SequenceGenerator(name = "merchant_user_id_seq", sequenceName = "merchant_user_id_seq", allocationSize = 1)
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "email")
    val email: String? = null,
    @Column(name = "password")
    val password: String? = null
)
