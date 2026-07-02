package com.speedyteller.reporting.api.domain.service.impl

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(var userService: UserServiceImpl) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val userModel = userService.findByEmail(email = username)
        return User(userModel.email!!, userModel.password, mutableListOf())
    }
}
