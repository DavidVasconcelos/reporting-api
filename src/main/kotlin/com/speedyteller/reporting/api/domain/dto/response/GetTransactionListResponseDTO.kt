package com.speedyteller.reporting.api.domain.dto.response

import com.speedyteller.reporting.api.domain.dto.AcquirerDTO
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListCustmerResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListIPNResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListMerchantResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListTransactionResponse
import com.speedyteller.reporting.api.extension.toStringPattern

data class GetTransactionListResponseDTO(
    val fx: FXResponseDTO,
    val customerInfo: GetTransactionListCustmerResponseDTO,
    val merchant: GetTransactionListMerchantResponseDTO,
    val ipn: GetTransactionListIPNResponseDTO,
    val transaction: GetTransactionListMerchantTransactionResponseDTO,
    val acquirer: AcquirerDTO,
    val refundable: Boolean
) {
    constructor(model: GetTransactionListResponse) :
            this(
                fx = FXResponseDTO(model = model.fx),
                customerInfo = GetTransactionListCustmerResponseDTO(model = model.customerInfo),
                merchant = GetTransactionListMerchantResponseDTO(model = model.merchant),
                ipn = GetTransactionListIPNResponseDTO(model = model.ipn),
                transaction = GetTransactionListMerchantTransactionResponseDTO(
                    merchant = GetTransactionListTransactionResponseDTO(
                        model = model.transaction.merchant
                    )
                ),
                acquirer = AcquirerDTO(model = model.acquirer),
                refundable = model.refundable
            )
}

data class GetTransactionListCustmerResponseDTO(
    var number: String? = null,
    var email: String? = null,
    var billingFirstName: String? = null,
    var billingLastName: String? = null
) {
    constructor(model: GetTransactionListCustmerResponse) : this() {
        this.number = model.number
        this.email = model.email
        this.billingFirstName = model.billingFirstName
        this.billingLastName = model.billingLastName
    }
}

data class GetTransactionListMerchantResponseDTO(var id: Long? = null, var name: String? = null) {
    constructor(model: GetTransactionListMerchantResponse) : this() {
        this.id = model.id
        this.name = model.name
    }
}

data class GetTransactionListIPNResponseDTO(var received: Boolean? = null) {
    constructor(model: GetTransactionListIPNResponse) : this() {
        this.received = model.received
    }
}

data class GetTransactionListMerchantTransactionResponseDTO(val merchant: GetTransactionListTransactionResponseDTO)

data class GetTransactionListTransactionResponseDTO(
    var referenceNo: String? = null,
    var status: String? = null,
    var operation: String? = null,
    var message: String? = null,
    var createdAt: String? = null,
    var transactionId: String? = null
) {
    constructor(model: GetTransactionListTransactionResponse) : this() {
        this.referenceNo = model.referenceNo
        this.status = model.status
        this.operation = model.operation
        this.message = model.message
        this.createdAt = model.createdAt?.toStringPattern()
        this.transactionId = model.transactionId
    }
}
