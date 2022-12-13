package com.speedyteller.reporting.api.controller

import com.speedyteller.reporting.api.domain.dto.request.LoginRequestDTO
import com.speedyteller.reporting.api.domain.dto.response.LoginResponseDTO
import com.speedyteller.reporting.api.domain.service.MerchantService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("merchant/user")
class MerchantController(val authenticationManager: AuthenticationManager, val merchantService: MerchantService) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Cacheable("logins")
    @PostMapping("login")
    fun login(@RequestBody loginRequestDTO: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        return try {
            val authenticate: Authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(loginRequestDTO.email, loginRequestDTO.password))
            val user = authenticate.principal as User
            ResponseEntity.ok().body(LoginResponseDTO(token = merchantService.login(user = user)))
        } catch (ex: BadCredentialsException) {
            logger.error(ex.message)
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    @CacheEvict(value = ["logins"], allEntries = true)
    @Scheduled(fixedRateString = "\${caching.spring.loginListTTL}")
    fun emptyLoginsCache() {
        logger.info("emptying logins cache")
    }
}
