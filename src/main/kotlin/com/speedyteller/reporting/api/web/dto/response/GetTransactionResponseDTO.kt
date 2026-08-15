package com.speedyteller.reporting.api.web.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GetTransactionResponseDTO(
    val acquirer: Acquirer,
    val customerInfo: CustomerInfo,
    val fx: FX,
    val merchant: Merchant,
    val transaction: MerchantTransaction,
) {
    data class Acquirer(var name: String? = null, var code: String? = null)

    data class CustomerInfo(
        var id: Long? = null,
        @JsonProperty("created_at")
        var createdAt: String? = null,
        @JsonProperty("updated_at")
        var updatedAt: String? = null,
        @JsonProperty("deleted_at")
        var deletedAt: String? = null,
        var number: String? = null,
        var expiryMonth: String? = null,
        var expiryYear: String? = null,
        var startMonth: String? = null,
        var startYear: String? = null,
        var issueNumber: String? = null,
        var email: String? = null,
        var birthday: String? = null,
        var gender: String? = null,
        var billingTitle: String? = null,
        var billingFirstName: String? = null,
        var billingLastName: String? = null,
        var billingCompany: String? = null,
        var billingAddress1: String? = null,
        var billingAddress2: String? = null,
        var billingCity: String? = null,
        var billingPostcode: String? = null,
        var billingState: String? = null,
        var billingCountry: String? = null,
        var billingPhone: String? = null,
        var billingFax: String? = null,
        var shippingTitle: String? = null,
        var shippingFirstName: String? = null,
        var shippingLastName: String? = null,
        var shippingCompany: String? = null,
        var shippingAddress1: String? = null,
        var shippingAddress2: String? = null,
        var shippingCity: String? = null,
        var shippingPostcode: String? = null,
        var shippingState: String? = null,
        var shippingCountry: String? = null,
        var shippingPhone: String? = null,
        var shippingFax: String? = null,
    )

    data class FX(val merchant: FXMerchant)

    data class FXMerchant(val originalAmount: Any?, val originalCurrency: String?)

    data class Merchant(var name: String? = null)

    data class MerchantTransaction(val merchant: TransactionInfo)

    data class TransactionInfo(
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
        @JsonProperty("updated_at")
        var updatedAt: String? = null,
        @JsonProperty("created_at")
        var createdAt: String? = null,
        var acquirerTransactionId: Long? = null,
        var code: String? = null,
        var message: String? = null,
        var transactionId: String? = null,
        var customerId: Long? = null,
        var refundable: Boolean? = null,
        var errorCode: String? = null,
        var agent: Agent? = null,
    )

    data class Agent(
        var id: Long? = null,
        var customerIp: String? = null,
        var customerUserAgent: String? = null,
        var merchantIp: String? = null,
    )
}
