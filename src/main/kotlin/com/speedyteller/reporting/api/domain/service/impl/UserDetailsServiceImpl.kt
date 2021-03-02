package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var userService: UserService

    override fun loadUserByUsername(username: String): UserDetails {

        val userModel = userService.findByEmail(email = username)

        return User(userModel.email, userModel.password, mutableListOf())
    }
}
