package com.speedyteller.reporting.api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "instant_payment_notification")
data class InstantPaymentNotificationEntity(
    @Id
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "transaction_id")
    val transactionId: String? = null,
    @Column(name = "received")
    val received: Boolean? = null
)
