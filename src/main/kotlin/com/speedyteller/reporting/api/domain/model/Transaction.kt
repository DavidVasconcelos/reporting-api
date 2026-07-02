package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.TransactionEntity
import java.io.Serial
import java.io.Serializable
import java.time.LocalDateTime

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
    var updatedAt: LocalDateTime? = null,
    var createdAt: LocalDateTime? = null,
    var acquirerTransactionId: Long? = null,
    var code: String? = null,
    var message: String? = null,
    var transactionId: String? = null,
    var customerId: Long? = null,
    var refundable: Boolean? = null,
    var errorCode: String? = null,
    var agent: AgentInfo? = null,

) : Serializable {
    constructor(entity: TransactionEntity) : this() {
        this.id = entity.id
        this.referenceNo = entity.referenceNo
        this.merchantId = entity.merchant?.id
        this.status = entity.status
        this.channel = entity.channel
        this.customData = entity.customData
        this.chainId = entity.chainId
        this.agentInfoId = entity.agentInfo?.id
        this.operation = entity.operation
        this.fxTransactionId = entity.fxTransaction?.id
        this.updatedAt = entity.updatedAt
        this.createdAt = entity.createdAt
        this.acquirerTransactionId = entity.acquirer?.id
        this.code = entity.code
        this.message = entity.message
        this.transactionId = entity.transactionId
        this.customerId = entity.customer?.id
        this.refundable = entity.refundable
        this.errorCode = entity.errorCode
        this.agent = entity.agentInfo?.let { AgentInfo(entity = it) } ?: AgentInfo()
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = 2503696395834428223L
    }
}
