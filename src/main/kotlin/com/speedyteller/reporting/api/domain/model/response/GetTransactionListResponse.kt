package com.speedyteller.reporting.api.domain.model.response

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.GetTransactionList
import java.time.LocalDateTime

data class GetTransactionListResponse(
    val fx: FXResponse,
    val customerInfo: GetTransactionListCustmerResponse,
    val merchant: GetTransactionListMerchantResponse,
    val ipn: GetTransactionListIPNResponse,
    val transaction: GetTransactionListMerchantTransactionResponse,
    val acquirer: Acquirer,
    val refundable: Boolean
) {
    constructor(model: GetTransactionList) :
            this(
                fx = FXResponse(
                    merchant = FXMerchant(
                        FXTransaction(
                            originalAmount = model.originalAmount,
                            originalCurrency = model.originalCurrency
                        )
                    )
                ),
                customerInfo = GetTransactionListCustmerResponse(
                    number = model.number,
                    email = model.email,
                    billingFirstName = model.billingFirstName,
                    billingLastName = model.billingLastName
                ),
                merchant = GetTransactionListMerchantResponse(
                    id = model.merchantId,
                    name = model.merchantName
                ),
                ipn = GetTransactionListIPNResponse(
                    received = model.received
                ),
                acquirer = Acquirer(
                    id = model.acquirerId,
                    name = model.acquirerName,
                    code = model.acquirerCode,
                    type = model.acquirerType
                ),
                transaction = GetTransactionListMerchantTransactionResponse(
                    merchant = GetTransactionListTransactionResponse(
                        referenceNo = model.referenceNo,
                        status = model.status,
                        operation = model.operation,
                        message = model.message,
                        created_at = model.created_at,
                        transactionId = model.transactionId
                    )
                ),
                refundable = model.refundable
            )
}

data class GetTransactionListCustmerResponse(
    var number: String? = null,
    var email: String? = null,
    var billingFirstName: String? = null,
    var billingLastName: String? = null
)

data class GetTransactionListMerchantResponse(var id: Long? = null, var name: String? = null)

data class GetTransactionListIPNResponse(var received: Boolean? = null)

data class GetTransactionListMerchantTransactionResponse(val merchant: GetTransactionListTransactionResponse)

data class GetTransactionListTransactionResponse(
    var referenceNo: String? = null,
    var status: String? = null,
    var operation: String? = null,
    var message: String? = null,
    var created_at: LocalDateTime? = null,
    var transactionId: String? = null
)
