package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.common.JwtTokenComponent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class MerchantService(val jwtTokenComponent: JwtTokenComponent) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun login(user: User): String = jwtTokenComponent.generateAccessToken(user = user).also {
        logger.info("Client ${user.username} successfully logged")
    }
}
