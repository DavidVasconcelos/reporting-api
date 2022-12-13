package com.speedyteller.reporting.api.domain.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailService(var userService: UserService) : UserDetailsService {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Cacheable("users")
    override fun loadUserByUsername(username: String): UserDetails {
        val userModel = userService.findByEmail(email = username)
        return User(userModel.email, userModel.password, mutableListOf())
    }

    @CacheEvict(value = ["users"], allEntries = true)
    @Scheduled(fixedRateString = "\${caching.spring.loginListTTL}")
    fun emptyUsersCache() {
        logger.info("emptying users cache")
    }
}
