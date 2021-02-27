package com.speedyteller.reporting.api.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "transaction")
data class TransactionEntity(

    @Id
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "reference_no")
    val referenceNo: String? = null,

    @Column(name = "merchant_id")
    val merchantId: Long? = null,

    @Column(name = "status")
    val status: String? = null,

    @Column(name = "channel")
    val channel: String? = null,

    @Column(name = "custom_data")
    val customData: String? = null,

    @Column(name = "chain_id")
    val chainId: String? = null,

    @Column(name = "agent_info_id")
    val agentInfoId: Long? = null,

    @Column(name = "operation")
    val operation: String? = null,

    @Column(name = "fx_transaction_td")
    val fxTransactionId: Long? = null,

    @Column(name = "updated_at")
    val updatedAt: String? = null,

    @Column(name = "created_at")
    val createdAt: String? = null,

    @Column(name = "acquirer_transaction_id")
    val acquirerTransactionId: Long? = null,

    @Column(name = "code")
    val code: Int? = null,

    @Column(name = "message")
    val message: String? = null,

    @Column(name = "transaction_id")
    val transactionId: String? = null,

    @Column(name = "customer_id")
    val customerId: Long? = null
)
