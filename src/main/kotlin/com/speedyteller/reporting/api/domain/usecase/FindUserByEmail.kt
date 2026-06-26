package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.User
import com.speedyteller.reporting.api.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindUserByEmail(private val userRepository: UserRepository) {

    @Transactional(readOnly = true)
    fun handle(email: String): User {
        val userEntity = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User not found with email: $email")
        return User(entity = userEntity)
    }
}
