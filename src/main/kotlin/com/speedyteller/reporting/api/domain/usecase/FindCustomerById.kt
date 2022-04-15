package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.extension.unwrap
import com.speedyteller.reporting.api.repository.CustomerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindCustomerById(private val transaction: Transaction) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handle(customerId: Long): Customer {
        return try {
            transaction.find(customerId)
        } catch (ex: DataIntegrityViolationException) {
            // Tries again when having a unique constraint error due to data concurrency
            logger.warn(ex.message)
            transaction.find(customerId)
        }
    }

    @Transactional
    @Component
    class Transaction(val customerRepository: CustomerRepository) {

        fun find(id: Long): Customer {
            val customerEntity = customerRepository.findById(id).unwrap()
                ?: throw NotFoundException("Customer not found")
            return Customer(entity = customerEntity)
        }
    }
}
