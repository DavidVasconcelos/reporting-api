package com.speedyteller.reporting.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.speedyteller.reporting.api.ReportingApiApplicationTests
import com.speedyteller.reporting.api.config.JwtTokenComponent
import com.speedyteller.reporting.api.config.PostgresContainerSetup
import com.speedyteller.reporting.api.domain.dto.CustomerDTO
import com.speedyteller.reporting.api.domain.dto.request.LoginRequestDTO
import com.speedyteller.reporting.api.domain.dto.response.GetCustomerResponseDTO
import com.speedyteller.reporting.api.domain.dto.response.LoginResponseDTO
import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.domain.service.CustomerService
import com.speedyteller.reporting.api.mock.MockTest
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.core.userdetails.User
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest(classes = [ReportingApiApplicationTests::class])
@ContextConfiguration(initializers = [PostgresContainerSetup::class])
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class MerchantControllerTest {

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jwtTokenComponent: JwtTokenComponent

    @Test
    fun `Test Login`() {

        val requestDTOJSON = mapper.writeValueAsString(LoginRequestDTO(email = "test", password = "teste")) as String

        val token = jwtTokenComponent
            .generateAccessToken(User("test", "test", mutableListOf()))

        val responseDTOJSON = mapper.writeValueAsString(LoginResponseDTO(token = token!!)) as String

        mockMvc.post("/merchant/user/login") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = requestDTOJSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(responseDTOJSON) }
        }
    }

    @Test
    fun `Test Login Unauthorized`() {

        val requestDTOJSON = mapper.writeValueAsString(LoginRequestDTO(email = "test", password = "teste123")) as String

        val token = jwtTokenComponent
            .generateAccessToken(User("test", "test", mutableListOf()))

        val responseDTOJSON = mapper.writeValueAsString(LoginResponseDTO(token = token!!)) as String

        mockMvc.post("/merchant/user/login") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = requestDTOJSON
        }.andExpect {
            status { isUnauthorized }
        }
    }
}
