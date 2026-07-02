package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.service.MerchantService
import com.speedyteller.reporting.api.security.JwtTokenComponent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class MerchantServiceImpl(val jwtTokenComponent: JwtTokenComponent) : MerchantService {

    override fun login(user: User): String = jwtTokenComponent.generateAccessToken(user = user).also {
        logger.info("Client ${user.username} successfully logged")
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
