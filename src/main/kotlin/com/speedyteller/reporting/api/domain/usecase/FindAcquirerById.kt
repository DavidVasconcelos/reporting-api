package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.extension.unwrap
import com.speedyteller.reporting.api.repository.AcquirerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindAcquirerById(private val transaction: Transaction) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handle(acquirerId: Long): Acquirer {
        return try {
            transaction.find(acquirerId)
        } catch (ex: DataIntegrityViolationException) {
            // Tries again when having a unique constraint error due to data concurrency
            logger.warn(ex.message)
            transaction.find(acquirerId)
        }
    }

    @Transactional
    @Component
    class Transaction(val acquirerRepository: AcquirerRepository) {

        fun find(id: Long): Acquirer {
            val acquirerEntity = acquirerRepository.findById(id).unwrap()
                ?: throw NotFoundException("Acquirer not found")
            return Acquirer(entity = acquirerEntity)
        }
    }
}
