package com.speedyteller.reporting.api.domain.entity

import java.time.LocalDateTime
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "transaction")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "transaction_id_seq", allocationSize = 1)
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
    val updatedAt: LocalDateTime? = null,
    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null,
    @Column(name = "acquirer_transaction_id")
    val acquirerTransactionId: Long? = null,
    @Column(name = "code")
    val code: String? = null,
    @Column(name = "message")
    val message: String? = null,
    @Column(name = "transaction_id")
    val transactionId: String? = null,
    @Column(name = "customer_id")
    val customerId: Long? = null,
    @Column(name = "refundable")
    var refundable: Boolean? = null,
    @Column(name = "error_code")
    var errorCode: String? = null
)
