package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.request.LoginRequest
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException

@IntegrationTest
class MerchantServiceTest {

    @Autowired
    private lateinit var merchantService: MerchantService

    @Test
    fun `Login should throw bad credentials exception when user does not exist or unauthorized`() {
        val loginRequest = LoginRequest(email = "ghost@email.com", password = "wrongPassword")

        val exception = assertThrows(BadCredentialsException::class.java) {
            merchantService.login(loginRequest)
        }

        assertEquals("Bad credentials", exception.message)
    }
}
