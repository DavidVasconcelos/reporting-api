package com.speedyteller.reporting.api.domain.model.response

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.GetTransaction
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.model.Transaction

data class GetTransactionResponse(
    val fx: FXResponse,
    val customerInfo: Customer,
    val acquirer: GetTransactionAcquirerResponse,
    val merchant: GetTransactionMerchantResponse,
    val transaction: GetTransactionMerchantTransactionResponse
) {
    constructor(model: GetTransaction) : this(
        fx = FXResponse(
            merchant = FXMerchant(
                FXTransaction(
                    originalAmount = model.fx.originalAmount,
                    originalCurrency = model.fx.originalCurrency
                )
            )
        ),
        customerInfo = model.customerInfo,
        acquirer = GetTransactionAcquirerResponse(acquirer = model.acquirer),
        merchant = GetTransactionMerchantResponse(merchant = model.merchant),
        transaction = GetTransactionMerchantTransactionResponse(merchant = model.transaction)
    )
}

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
