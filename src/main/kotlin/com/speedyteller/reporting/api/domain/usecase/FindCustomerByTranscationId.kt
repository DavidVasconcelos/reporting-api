package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.extension.unwrap
import com.speedyteller.reporting.api.repository.CustomerRepository
import com.speedyteller.reporting.api.repository.TransactionRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindCustomerByTranscationId(private val transaction: Transaction) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handle(transactionId: String): Customer {
        return try {
            transaction.find(transactionId)
        } catch (ex: DataIntegrityViolationException) {
            // Tries again when having a unique constraint error due to data concurrency
            logger.warn(ex.message)
            transaction.find(transactionId)
        }
    }

    @Transactional
    @Component
    class Transaction(val transactionRepository: TransactionRepository, val customerRepository: CustomerRepository) {

        fun find(transactionId: String): Customer {
            val transactionEntity = transactionRepository.findByTransactionId(transactionId = transactionId)
                ?: throw NotFoundException("Transaction not found")
            val customerEntity = transactionEntity.customerId?.let { customerRepository.findById(it).unwrap() }
                ?: throw NotFoundException("Customer not found")
            return Customer(entity = customerEntity)
        }
    }
}
