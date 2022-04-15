package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.domain.model.response.FXMerchant
import com.speedyteller.reporting.api.domain.model.response.FXResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionAcquirerResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionMerchantResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionMerchantTransactionResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionResponse
import com.speedyteller.reporting.api.domain.usecase.FindAcquirerById
import com.speedyteller.reporting.api.domain.usecase.FindCustomerById
import com.speedyteller.reporting.api.domain.usecase.FindFXTransactionById
import com.speedyteller.reporting.api.domain.usecase.FindMerchantById
import com.speedyteller.reporting.api.domain.usecase.FindTransactionById
import com.speedyteller.reporting.api.domain.usecase.GetTransactions
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TransactionService(
    val findTransactionById: FindTransactionById,
    val findFXTransactionById: FindFXTransactionById,
    val findCustomerById: FindCustomerById,
    val findAcquirerById: FindAcquirerById,
    val findMerchantById: FindMerchantById,
    val getTransactions: GetTransactions
) {

    // refazer
    fun getTransaction(transactionId: String): GetTransactionResponse {
        val transaction = findTransactionById.handle(transactionId = transactionId)
        val fxTransaction =
            transaction.fxTransactionId?.let { getFxTransaction(fxTransactionId = it) } ?: FXTransaction()
        val customer = transaction.customerId?.let { getCustomer(customerId = it) } ?: Customer()
        val acquirer = transaction.acquirerTransactionId?.let { getAcquirer(acquirerId = it) } ?: Acquirer()
        val merchant = transaction.merchantId?.let { getMerchant(merchantId = it) } ?: Merchant()
        return GetTransactionResponse(
            fx = FXResponse(merchant = FXMerchant(fxTransaction = fxTransaction)),
            customerInfo = customer,
            acquirer = GetTransactionAcquirerResponse(acquirer = acquirer),
            merchant = GetTransactionMerchantResponse(merchant = merchant),
            transaction = GetTransactionMerchantTransactionResponse(merchant = transaction)
        )
    }

    fun getTransactionList(
        request: GetTransactionListRequest,
        page: Pageable
    ): List<GetTransactionListResponse> =
        getTransactions.handle(request = request, page = page).map { GetTransactionListResponse(model = it) }

    private fun getFxTransaction(fxTransactionId: Long) =
        findFXTransactionById.handle(fxTransactionId = fxTransactionId)

    private fun getCustomer(customerId: Long) =
        findCustomerById.handle(customerId = customerId)

    private fun getAcquirer(acquirerId: Long) =
        findAcquirerById.handle(acquirerId = acquirerId)

    private fun getMerchant(merchantId: Long) =
        findMerchantById.handle(merchantId = merchantId)
}
