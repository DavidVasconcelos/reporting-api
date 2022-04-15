package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.extension.unwrap
import com.speedyteller.reporting.api.repository.MerchantRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindMerchantById(private val transaction: Transaction) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handle(merchantId: Long): Merchant {
        return try {
            transaction.find(merchantId)
        } catch (ex: DataIntegrityViolationException) {
            // Tries again when having a unique constraint error due to data concurrency
            logger.warn(ex.message)
            transaction.find(merchantId)
        }
    }

    @Transactional
    @Component
    class Transaction(val merchantRepository: MerchantRepository) {

        fun find(id: Long): Merchant {
            val merchantEntity = merchantRepository.findById(id).unwrap()
                ?: throw NotFoundException("Merchant not found")
            return Merchant(entity = merchantEntity)
        }
    }
}
