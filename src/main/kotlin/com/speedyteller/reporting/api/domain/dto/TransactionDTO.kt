package com.speedyteller.reporting.api.domain.dto

import com.speedyteller.reporting.api.domain.model.Transaction
import java.time.LocalDateTime

data class TransactionDTO(

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
    var updated_at: LocalDateTime? = null,
    var created_at: LocalDateTime? = null,
    var acquirerTransactionId: Long? = null,
    var code: Int? = null,
    var message: String? = null,
    var transactionId: String? = null,
    var customerId: Long? = null,
    var agent: AgentInfoDTO? = null
) {
    constructor(model: Transaction) : this() {
        this.id = model.id
        this.referenceNo = model.referenceNo
        this.merchantId = model.merchantId
        this.status = model.status
        this.channel = model.channel
        this.customData = model.customData
        this.chainId = model.chainId
        this.agentInfoId = model.agentInfoId
        this.operation = model.operation
        this.fxTransactionId = model.fxTransactionId
        this.updated_at = model.updated_at
        this.created_at = model.created_at
        this.acquirerTransactionId = model.acquirerTransactionId
        this.code = model.code
        this.message = model.message
        this.transactionId = model.transactionId
        this.customerId = model.customerId
        this.agent = model.agent?.let { AgentInfoDTO(model = it) }
    }
}
