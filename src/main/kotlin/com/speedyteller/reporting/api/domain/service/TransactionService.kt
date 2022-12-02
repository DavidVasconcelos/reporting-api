package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionResponse
import com.speedyteller.reporting.api.domain.usecase.FindTransactionById
import com.speedyteller.reporting.api.domain.usecase.GetTransactions
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TransactionService(
    val findTransactionById: FindTransactionById,
    val getTransactions: GetTransactions
) {

    fun getTransaction(transactionId: String): GetTransactionResponse {
        val transaction = findTransactionById.handle(transactionId = transactionId)
        return GetTransactionResponse(model = transaction)
    }

    fun getTransactionList(
        request: GetTransactionListRequest,
        page: Pageable
    ): List<GetTransactionListResponse> =
        getTransactions.handle(request = request, page = page).map { GetTransactionListResponse(model = it) }
}
