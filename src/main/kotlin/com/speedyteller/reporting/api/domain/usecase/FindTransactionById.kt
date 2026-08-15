package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.Transaction
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.repository.jpa.TransactionRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindTransactionById(private val transactionRepository: TransactionRepository) {

    @Transactional(readOnly = true)
    fun handle(transactionId: String): Transaction {
        val entity = transactionRepository.findByTransactionId(transactionId)
            ?: throw NotFoundException("Transaction not found")

        return Transaction(entity = entity)
    }
}
