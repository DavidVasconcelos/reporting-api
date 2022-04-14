package com.speedyteller.reporting.api.domain.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailService(var userService: UserService) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val userModel = userService.findByEmail(email = username)
        return User(userModel.email, userModel.password, mutableListOf())
    }
}
