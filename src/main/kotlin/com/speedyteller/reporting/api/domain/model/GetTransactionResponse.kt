package com.speedyteller.reporting.api.domain.model

data class GetTransactionResponse(
    val fx: FXResponse,
    val customerInfo: Customer,
    val acquirer: GetTransactionAcquirerResponse,
    val merchant: GetTransactionMerchantResponse,
    val transaction: GetTransactionMerchantTransactionResponse
)

data class GetTransactionAcquirerResponse(var name: String? = null, var code: String? = null) {
    constructor(acquirer: Acquirer) : this() {
        this.name = acquirer.name
        this.code = acquirer.code
    }
}

data class GetTransactionMerchantResponse(var name: String? = null) {
    constructor(merchant: Merchant) : this() {
        this.name = merchant.name
    }
}

data class GetTransactionMerchantTransactionResponse(val merchant: Transaction)
