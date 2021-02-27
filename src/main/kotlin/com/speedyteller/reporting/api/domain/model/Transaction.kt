package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.TransactionEntity

data class Transaction(

    var id: Long? = null,
    var referenceNo: String? = null,
    var merchantId: Long? = null,
    var status: String? = null,
    var channel: String? = null,
    var customData: String? = null,
    var chainId: String? = null,
    var agentInfoId: Long? = null,
    var operation: String? = null,
    var fxTransactionId: Long? = null,
    var updatedAt: String? = null,
    var createdAt: String? = null,
    var acquirerTransactionId: Long? = null,
    var code: Int? = null,
    var message: String? = null,
    var transactionId: String? = null,
    var customerId: Long? = null
) {
    constructor(entity: TransactionEntity) : this() {
        this.id = entity.id
        this.referenceNo = entity.referenceNo
        this.merchantId = entity.merchantId
        this.status = entity.status
        this.channel = entity.channel
        this.customData = entity.customData
        this.chainId = entity.chainId
        this.agentInfoId = entity.agentInfoId
        this.operation = entity.operation
        this.fxTransactionId = entity.fxTransactionId
        this.updatedAt = entity.updatedAt
        this.createdAt = entity.createdAt
        this.acquirerTransactionId = entity.acquirerTransactionId
        this.code = entity.code
        this.message = entity.message
        this.transactionId = entity.transactionId
        this.customerId = entity.customerId
    }
}
