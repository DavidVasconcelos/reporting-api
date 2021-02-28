package com.speedyteller.reporting.api.domain.model.response

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.InstantPaymentNotification
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.model.Transaction
import java.time.LocalDateTime

data class GetTransactionListResponse(
    val fx: FXResponse,
    val customerInfo: GetTransactionListCustmerResponse,
    val merchant: GetTransactionListMerchantResponse,
    val ipn: GetTransactionListIPNResponse,
    val transaction: GetTransactionListMerchantTransactionResponse,
    val acquirer: Acquirer,
    val refundable: Boolean
)

data class GetTransactionListCustmerResponse(
    var number: String? = null,
    var email: String? = null,
    var billingFirstName: String? = null,
    var billingLastName: String? = null
) {
    constructor(customer: Customer) : this() {
        this.number = customer.number
        this.email = customer.email
        this.billingFirstName = customer.billingFirstName
        this.billingLastName = customer.billingFirstName
    }
}

data class GetTransactionListMerchantResponse(var id: Long? = null, var name: String? = null) {
    constructor(merchant: Merchant) : this() {
        this.id = merchant.id
        this.name = merchant.name
    }
}

data class GetTransactionListIPNResponse(var received: Boolean? = null) {
    constructor(instantPaymentNotification: InstantPaymentNotification) : this() {
        this.received = instantPaymentNotification.received
    }
}

data class GetTransactionListMerchantTransactionResponse(val merchant: GetTransactionListTransactionResponse)

data class GetTransactionListTransactionResponse(
    var referenceNo: String? = null,
    var status: String? = null,
    var operation: String? = null,
    var message: String? = null,
    var created_at: LocalDateTime? = null,
    var transactionId: String? = null
) {
    constructor(transaction: Transaction) : this() {
        this.referenceNo = transaction.referenceNo
        this.status = transaction.status
        this.operation = transaction.operation
        this.message = transaction.message
        this.created_at = transaction.created_at
        this.transactionId = transaction.transactionId
    }
}
