package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionResponse
import org.springframework.data.domain.Page

interface TransactionService {

    fun getTransaction(transactionId: String): GetTransactionResponse
    fun getTransactionList(request: GetTransactionListRequest): Page<GetTransactionListResponse>
}
