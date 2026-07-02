package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionResponse
import com.speedyteller.reporting.api.domain.service.TransactionService
import com.speedyteller.reporting.api.domain.usecase.FindTransactionById
import com.speedyteller.reporting.api.domain.usecase.GetTransactions
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl(val findTransactionById: FindTransactionById, val getTransactions: GetTransactions) :
    TransactionService {

    @Cacheable(value = ["transactions"], keyGenerator = "customKeyGenerator")
    override fun getTransaction(transactionId: String): GetTransactionResponse {
        logger.info("Looking for transaction $transactionId")
        val transaction = findTransactionById.handle(transactionId = transactionId)
        return GetTransactionResponse(model = transaction)
    }

    override fun getTransactionList(
        request: GetTransactionListRequest,
        page: Pageable,
    ): List<GetTransactionListResponse> {
        logger.info("Looking for transactions with request $request")
        return getTransactions.handle(request = request, page = page).map {
            GetTransactionListResponse(
                model = it,
            )
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
