package com.speedyteller.reporting.api.domain.model.response

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.GetTransaction
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.model.Transaction
import java.io.Serial
import java.io.Serializable

data class GetTransactionResponse(
    val fx: FXResponse,
    val customerInfo: Customer,
    val acquirer: GetTransactionAcquirerResponse,
    val merchant: GetTransactionMerchantResponse,
    val transaction: GetTransactionMerchantTransactionResponse,
) : Serializable {
    constructor(model: GetTransaction) : this(
        fx = FXResponse(
            merchant = FXMerchant(
                FXTransaction(
                    originalAmount = model.fx.originalAmount,
                    originalCurrency = model.fx.originalCurrency,
                ),
            ),
        ),
        customerInfo = model.customerInfo,
        acquirer = GetTransactionAcquirerResponse(acquirer = model.acquirer),
        merchant = GetTransactionMerchantResponse(merchant = model.merchant),
        transaction = GetTransactionMerchantTransactionResponse(merchant = model.transaction),
    )

    companion object {
        @Serial
        private const val serialVersionUID: Long = -935747305312300949L
    }
}

data class GetTransactionAcquirerResponse(var name: String? = null, var code: String? = null) : Serializable {
    constructor(acquirer: Acquirer) : this() {
        this.name = acquirer.name
        this.code = acquirer.code
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = 3282991154520214543L
    }
}

data class GetTransactionMerchantResponse(var name: String? = null) : Serializable {
    constructor(merchant: Merchant) : this() {
        this.name = merchant.name
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = -791905958450195934L
    }
}

data class GetTransactionMerchantTransactionResponse(val merchant: Transaction) : Serializable {
    companion object {
        @Serial
        private const val serialVersionUID: Long = 2543900951670592095L
    }
}
