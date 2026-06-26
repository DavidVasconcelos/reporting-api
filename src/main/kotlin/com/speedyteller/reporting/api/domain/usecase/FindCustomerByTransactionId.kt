package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.repository.TransactionRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindCustomerByTransactionId(private val transactionRepository: TransactionRepository) {

    @Transactional(readOnly = true)
    fun handle(transactionId: String): Customer {
        val transactionEntity =
            transactionRepository.findByTransactionId(transactionId = transactionId)
                ?: throw NotFoundException("Transaction not found")

        return Customer(entity = transactionEntity.customer!!)
    }
}
