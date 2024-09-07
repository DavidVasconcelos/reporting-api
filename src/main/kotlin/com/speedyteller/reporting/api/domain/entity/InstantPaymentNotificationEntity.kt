package com.speedyteller.reporting.api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "instant_payment_notification")
data class InstantPaymentNotificationEntity(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "instant_payment_notification_id_seq"
    )
    @SequenceGenerator(
        name = "instant_payment_notification_id_seq",
        sequenceName = "instant_payment_notification_id_seq",
        allocationSize = 1
    )
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "transaction_id")
    val transactionId: String? = null,
    @Column(name = "received")
    val received: Boolean? = null
)
