package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.User
import com.speedyteller.reporting.api.domain.service.UserService
import com.speedyteller.reporting.api.domain.usecase.FindUserByEmail
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val findUserByEmail: FindUserByEmail) : UserService {

    override fun findByEmail(email: String): User {
        logger.info("Looking for user by email $email")
        return findUserByEmail.handle(email = email)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
