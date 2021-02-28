package com.speedyteller.reporting.api.domain.dto.response

import com.speedyteller.reporting.api.domain.dto.CustomerDTO
import com.speedyteller.reporting.api.domain.dto.TransactionDTO
import com.speedyteller.reporting.api.domain.model.response.GetTransactionAcquirerResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionMerchantResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionMerchantTransactionResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionResponse

data class GetTransactionResponseDTO(
    val fx: FXResponseDTO,
    val customerInfo: CustomerDTO,
    val acquirer: GetTransactionAcquirerResponseDTO,
    val merchant: GetTransactionMerchantResponseDTO,
    val transaction: GetTransactionMerchantTransactionResponseDTO
) {
    constructor(
        model: GetTransactionResponse
    ) : this(
        fx = FXResponseDTO(model = model.fx),
        customerInfo = CustomerDTO(model = model.customerInfo),
        acquirer = GetTransactionAcquirerResponseDTO(model = model.acquirer),
        merchant = GetTransactionMerchantResponseDTO(model = model.merchant),
        transaction = GetTransactionMerchantTransactionResponseDTO(model = model.transaction)
    )
}

data class GetTransactionAcquirerResponseDTO(var name: String? = null, var code: String? = null) {

    constructor(model: GetTransactionAcquirerResponse) : this() {
        this.name = model.name
        this.code = model.code
    }
}

data class GetTransactionMerchantResponseDTO(var name: String? = null) {

    constructor(model: GetTransactionMerchantResponse) : this() {
        this.name = model.name
    }
}

data class GetTransactionMerchantTransactionResponseDTO(val merchant: TransactionDTO) {

    constructor(model: GetTransactionMerchantTransactionResponse) :
            this(merchant = TransactionDTO(model = model.merchant))
}
