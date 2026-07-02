package com.speedyteller.reporting.api.domain.service.impl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(var userService: UserServiceImpl) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        logger.info("Loading user by username: $username")
        val userModel = userService.findByEmail(email = username)
        return User(userModel.email!!, userModel.password, mutableListOf())
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
