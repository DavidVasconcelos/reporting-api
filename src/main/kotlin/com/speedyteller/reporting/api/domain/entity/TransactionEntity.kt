package com.speedyteller.reporting.api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "transaction")
class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    @SequenceGenerator(
        name = "transaction_id_seq",
        sequenceName = "transaction_id_seq",
        allocationSize = 1,
    )
    @Column(name = "id")
    val id: Long? = null

    @Column(name = "reference_no")
    val referenceNo: String? = null

    @Column(name = "status")
    val status: String? = null

    @Column(name = "channel")
    val channel: String? = null

    @Column(name = "custom_data")
    val customData: String? = null

    @Column(name = "chain_id")
    val chainId: String? = null

    @Column(name = "operation")
    val operation: String? = null

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null

    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null

    @Column(name = "code")
    val code: String? = null

    @Column(name = "message")
    val message: String? = null

    @Column(name = "transaction_id")
    val transactionId: String? = null

    @Column(name = "refundable")
    var refundable: Boolean? = null

    @Column(name = "error_code")
    var errorCode: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    var customer: CustomerEntity? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    var merchant: MerchantEntity? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acquirer_transaction_id")
    var acquirer: AcquirerEntity? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fx_transaction_td")
    var fxTransaction: FXTransactionEntity? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_info_id")
    var agentInfo: AgentInfoEntity? = null
}
