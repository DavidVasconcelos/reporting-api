package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.extension.unwrap
import com.speedyteller.reporting.api.repository.FXTransactionRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindFXTransactionById(private val transaction: Transaction) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handle(fxTransactionId: Long): FXTransaction {
        return try {
            transaction.find(fxTransactionId)
        } catch (ex: DataIntegrityViolationException) {
            // Tries again when having a unique constraint error due to data concurrency
            logger.warn(ex.message)
            transaction.find(fxTransactionId)
        }
    }

    @Transactional
    @Component
    class Transaction(val fxTransactionRepository: FXTransactionRepository) {

        fun find(id: Long): FXTransaction {
            val fxTransactionEntity = fxTransactionRepository.findById(id).unwrap()
                ?: throw NotFoundException("FXTransaction not found")
            return FXTransaction(entity = fxTransactionEntity)
        }
    }
}
