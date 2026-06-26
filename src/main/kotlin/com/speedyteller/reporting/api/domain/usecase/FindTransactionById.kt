package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.GetTransaction
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.model.Transaction
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.repository.TransactionRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindTransactionById(private val transactionRepository: TransactionRepository) {

    @Transactional(readOnly = true)
    fun handle(transactionId: String): GetTransaction {
        val entity = transactionRepository.findByTransactionId(transactionId)
            ?: throw NotFoundException("Transaction not found")

        return GetTransaction(
            transaction = Transaction(entity = entity),
            fx = entity.fxTransaction?.let { FXTransaction(entity = it) } ?: FXTransaction(),
            customerInfo = entity.customer?.let { Customer(entity = it) } ?: Customer(),
            acquirer = entity.acquirer?.let { Acquirer(entity = it) } ?: Acquirer(),
            merchant = entity.merchant?.let { Merchant(entity = it) } ?: Merchant(),
        )
    }
}
