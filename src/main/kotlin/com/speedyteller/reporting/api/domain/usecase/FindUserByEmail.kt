package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.User
import com.speedyteller.reporting.api.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindUserByEmail(private val transaction: Transaction) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handle(email: String): User {
        return try {
            transaction.find(email)
        } catch (ex: DataIntegrityViolationException) {
            // Tries again when having a unique constraint error due to data concurrency
            logger.warn(ex.message)
            transaction.find(email)
        }
    }

    @Transactional
    @Component
    class Transaction(val userRepository: UserRepository) {

        fun find(email: String): User {
            val userEntity = userRepository.findByEmail(email)
                ?: throw UsernameNotFoundException("User not found with email: $email")
            return User(entity = userEntity)
        }
    }
}
