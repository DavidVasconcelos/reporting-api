package com.speedyteller.reporting.api.controller

import com.speedyteller.reporting.api.config.JwtTokenComponent
import com.speedyteller.reporting.api.domain.dto.request.LoginRequestDTO
import com.speedyteller.reporting.api.domain.dto.response.LoginResponseDTO
import com.speedyteller.reporting.api.security.SecurityConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin
@RequestMapping("merchant/user")
class MerchantController(val authenticationManager: AuthenticationManager, val jwtTokenComponent: JwtTokenComponent) {

    private var logger: Logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    @PostMapping("login")
    fun login(@RequestBody loginRequestDTO: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        return try {
            val authenticate: Authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(loginRequestDTO.email, loginRequestDTO.password))
            val user = authenticate.principal as User
            ResponseEntity.ok().body(LoginResponseDTO(token = jwtTokenComponent.generateAccessToken(user)))
        } catch (ex: BadCredentialsException) {
            logger.error(ex.message)
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }
}
