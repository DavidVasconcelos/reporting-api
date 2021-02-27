package com.speedyteller.reporting.api.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "instant_payment_notification")
data class InstantPaymentNotification(

    @Id
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "transaction_id")
    val transactionId: Int? = null,

    @Column(name = "received")
    val received: Boolean? = null
)
