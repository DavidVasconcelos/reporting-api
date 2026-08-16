package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.Transaction
import com.speedyteller.reporting.api.domain.model.TransactionSummary
import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.domain.service.TransactionService
import com.speedyteller.reporting.api.domain.usecase.FindTransactionById
import com.speedyteller.reporting.api.domain.usecase.GetTransactionSummary
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl(
    val findTransactionById: FindTransactionById,
    val getTransactionSummary: GetTransactionSummary,
) : TransactionService {

    @Cacheable(value = ["transactions"], keyGenerator = "customKeyGenerator")
    override fun getTransaction(transactionId: String): Transaction {
        logger.info("Looking for transaction by id: $transactionId")
        return findTransactionById.handle(transactionId = transactionId)
    }

    override fun getTransactionList(request: GetTransactionListRequest, page: Pageable): List<TransactionSummary> {
        logger.info("Looking for transaction summary with request $request")
        return getTransactionSummary.handle(request = request, page = page)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
