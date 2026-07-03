package com.speedyteller.reporting.api.controller

import com.speedyteller.reporting.api.domain.dto.request.LoginRequestDTO
import com.speedyteller.reporting.api.domain.dto.response.LoginResponseDTO
import com.speedyteller.reporting.api.domain.model.request.LoginRequest
import com.speedyteller.reporting.api.domain.service.MerchantService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("merchant/user")
class MerchantController(val merchantService: MerchantService) {

    @PostMapping("login")
    fun login(@RequestBody loginRequestDTO: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        logger.info("Processing login")
        val loginResponse = merchantService.login(LoginRequest(dto = loginRequestDTO))

        return ResponseEntity
            .ok()
            .body(LoginResponseDTO(loginResponse))
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
