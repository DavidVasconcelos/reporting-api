package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.GetTransactionResponse

interface TransactionService {

    fun getTransaction(transactionId: String): GetTransactionResponse
}
