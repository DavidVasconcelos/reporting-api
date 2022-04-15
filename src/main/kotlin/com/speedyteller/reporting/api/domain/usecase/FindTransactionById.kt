package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.repository.TransactionRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import com.speedyteller.reporting.api.domain.model.Transaction as TransactionModel

@Component
class FindTransactionById(private val transaction: Transaction) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handle(transactionId: String): TransactionModel {
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
    class Transaction(val transactionRepository: TransactionRepository, val findAgentInfoById: FindAgentInfoById) {

        fun find(id: String): TransactionModel {
            val transactionEntity = transactionRepository.findByTransactionId(transactionId = id)
                ?: throw NotFoundException("Transaction not found")
            val transactionModel = TransactionModel(entity = transactionEntity)
            transactionModel.agent = transactionModel.agentInfoId?.let { findAgentInfoById.handle(it) }
            return transactionModel
        }
    }
}
