package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.request.LoginRequest
import com.speedyteller.reporting.api.domain.model.response.LoginResponse
import com.speedyteller.reporting.api.domain.service.MerchantService
import com.speedyteller.reporting.api.security.JwtTokenComponent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority // 🚀 Don't forget this import!
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class MerchantServiceImpl(
    val authenticationManager: AuthenticationManager,
    val jwtTokenComponent: JwtTokenComponent,
    @Value("\${security.jwt-expiration-time}") private val jwtExpirationTime: Int,
    @Value("\${security.role}") private val role: String,
) : MerchantService {

    override fun login(loginRequest: LoginRequest): LoginResponse {
        val user = authenticate(loginRequest)
        logger.info("Client ${user.username} successfully logged in")

        val userWithNewRole = updateAuthorities(user)

        val accessToken = jwtTokenComponent.generateAccessToken(user = userWithNewRole)
        return LoginResponse(token = accessToken, expiresIn = jwtExpirationTime)
    }

    private fun authenticate(loginRequest: LoginRequest): User {
        val authenticate: Authentication = authenticationManager
            .authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequest.email,
                    loginRequest.password,
                ),
            )
        return authenticate.principal as User
    }

    private fun updateAuthorities(user: User): User {
        val updatedAuthorities = user.authorities.toMutableList()
        updatedAuthorities.add(SimpleGrantedAuthority(role))
        val userWithNewRole = User(
            user.username,
            user.password ?: "",
            updatedAuthorities,
        )
        return userWithNewRole
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
