package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.InstantPaymentNotificationEntity

data class InstantPaymentNotification(

    var id: Long? = null,
    var transactionId: Int? = null,
    var received: Boolean? = null
) {
    constructor(enity: InstantPaymentNotificationEntity) : this() {
        this.id = enity.id
        this.transactionId = enity.transactionId
        this.received = enity.received
    }
}
