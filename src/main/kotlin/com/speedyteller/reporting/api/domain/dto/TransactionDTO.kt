package com.speedyteller.reporting.api.domain.dto

import com.speedyteller.reporting.api.domain.model.Transaction
import com.speedyteller.reporting.api.extension.toStringPattern

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
    var updated_at: String? = null,
    var created_at: String? = null,
    var acquirerTransactionId: Long? = null,
    var code: String? = null,
    var message: String? = null,
    var transactionId: String? = null,
    var customerId: Long? = null,
    var refundable: Boolean? = null,
    var errodCode: String? = null,
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
        this.updated_at = model.updated_at?.toStringPattern()
        this.created_at = model.created_at?.toStringPattern()
        this.acquirerTransactionId = model.acquirerTransactionId
        this.code = model.code
        this.message = model.message
        this.transactionId = model.transactionId
        this.customerId = model.customerId
        this.refundable = model.refundable
        this.errodCode = model.errodCode
        this.agent = model.agent?.let { AgentInfoDTO(model = it) }
    }
}
