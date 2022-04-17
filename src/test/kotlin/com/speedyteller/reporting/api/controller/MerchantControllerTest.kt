package com.speedyteller.reporting.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.speedyteller.reporting.api.config.JwtTokenComponent
import com.speedyteller.reporting.api.domain.dto.request.LoginRequestDTO
import com.speedyteller.reporting.api.domain.service.MerchantService
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import com.speedyteller.reporting.api.support.annotations.andResultBodyMatches
import com.speedyteller.reporting.api.support.json.json
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.security.core.userdetails.User
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@IntegrationTest
@AutoConfigureMockMvc
class MerchantControllerTest {

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
        val requestDTOJSON = mapper.writeValueAsString(LoginRequestDTO(email = "test", password = "teste")) as String

        every { service.login(any()) } returns expectedToken

        mockMvc.perform(
            MockMvcRequestBuilders.post("/merchant/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestDTOJSON)
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andResultBodyMatches(
            json = json {
                "token" to expectedToken
                "status" to "APPROVED"
            }
        )
    }

    @Test
    fun `Returns unauthorized when user does not exist`() {
        val requestDTOJSON = mapper.writeValueAsString(LoginRequestDTO(email = "test", password = "teste123")) as String

        mockMvc.post("/merchant/user/login") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = requestDTOJSON
        }.andExpect {
            status { isUnauthorized() }
        }
    }
}
