package com.speedyteller.reporting.api.controller

import com.speedyteller.reporting.api.config.JwtTokenComponent
import com.speedyteller.reporting.api.domain.dto.request.LoginRequestDTO
import com.speedyteller.reporting.api.domain.dto.response.LoginResponseDTO
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
class MerchantController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtTokenComponent: JwtTokenComponent

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @PostMapping("login")
    fun login(@RequestBody loginRequestDTO: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {

        return try {

            val authenticate: Authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(loginRequestDTO.email, loginRequestDTO.password))

            val user = authenticate.principal as User

            ResponseEntity.ok().body(LoginResponseDTO(token = jwtTokenComponent.generateAccessToken(user)!!))

        } catch (ex: BadCredentialsException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

}
