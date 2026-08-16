package com.speedyteller.reporting.api.web.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GetTransactionSummaryResponseDTO(
    val acquirer: Acquirer,
    val customerInfo: CustomerInfo,
    val fx: FX,
    val merchant: Merchant,
    val ipn: IPN,
    val refundable: Boolean,
    val transaction: MerchantTransaction,
) {
    data class Acquirer(
        var id: Long? = null,
        var name: String? = null,
        var code: String? = null,
        var type: String? = null,
    )

    data class CustomerInfo(
        var number: String? = null,
        var email: String? = null,
        var billingFirstName: String? = null,
        var billingLastName: String? = null,
    )

    data class FX(val merchant: FXMerchant)

    data class FXMerchant(val originalAmount: Any?, val originalCurrency: String?)

    data class Merchant(var id: Long? = null, var name: String? = null)

    data class IPN(var received: Boolean? = null)

    data class MerchantTransaction(val merchant: TransactionInfo)

    data class TransactionInfo(
        var referenceNo: String? = null,
        var status: String? = null,
        var operation: String? = null,
        var message: String? = null,
        @JsonProperty("created_at")
        var createdAt: String? = null,
        var transactionId: String? = null,
    )
}
