package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.Transaction
import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListResponse
import org.springframework.data.domain.Pageable

interface TransactionService {

    fun getTransaction(transactionId: String): Transaction
    fun getTransactionList(request: GetTransactionListRequest, page: Pageable): List<GetTransactionListResponse>
}
