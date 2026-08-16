package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.Transaction
import com.speedyteller.reporting.api.domain.model.TransactionSummary
import com.speedyteller.reporting.api.domain.model.request.TransactionSummaryRequest
import org.springframework.data.domain.Pageable

interface TransactionService {

    fun getTransaction(transactionId: String): Transaction
    fun getTransactionList(request: TransactionSummaryRequest, page: Pageable): List<TransactionSummary>
}
