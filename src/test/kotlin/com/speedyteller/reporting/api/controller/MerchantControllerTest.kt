package com.speedyteller.reporting.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.speedyteller.reporting.api.domain.dto.request.LoginRequestDTO
import com.speedyteller.reporting.api.domain.model.response.LoginResponse
import com.speedyteller.reporting.api.domain.service.MerchantService
import com.speedyteller.reporting.api.security.JwtTokenComponent
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import com.speedyteller.reporting.api.support.annotations.andResultBodyMatches
import com.speedyteller.reporting.api.support.json.json
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.User
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@IntegrationTest
@AutoConfigureMockMvc
@Import(ObjectMapper::class)
class MerchantControllerTest {

    @Value("\${security.jwt-expiration-time}")
    private var jwtExpirationTime: Int = 0

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var service: MerchantService

    @Autowired
    private lateinit var jwtTokenComponent: JwtTokenComponent

    @Test
    fun `Successful test login`() {
        val expectedToken =
            jwtTokenComponent.generateAccessToken(User("test", "test", mutableListOf()))
        val expectedResponse = LoginResponse(expectedToken, expiresIn = jwtExpirationTime)
        val requestDTOJSON =
            mapper.writeValueAsString(LoginRequestDTO(email = "test", password = "teste")) as String

        every { service.login(any()) } returns expectedResponse

        mockMvc.perform(
            MockMvcRequestBuilders.post("/merchant/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestDTOJSON),
        ).andExpect(
            MockMvcResultMatchers.status().isOk,
        ).andResultBodyMatches(
            json = json {
                "access_token" to expectedToken
                "token_type" to "Bearer"
                "expires_in" to 3599
            },
        )
    }

    @Test
    fun `Returns unauthorized when user does not exist`() {
        val requestDTOJSON = mapper.writeValueAsString(
            LoginRequestDTO(
                email = "test",
                password = "teste123",
            ),
        ) as String

        every { service.login(any()) } throws BadCredentialsException("Bad Credentials")

        mockMvc.perform(
            MockMvcRequestBuilders.post("/merchant/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestDTOJSON),
        ).andExpect(
            MockMvcResultMatchers.status().isUnauthorized,
        )
    }
}
