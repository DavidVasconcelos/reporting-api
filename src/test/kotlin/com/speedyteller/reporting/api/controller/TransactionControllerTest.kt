package com.speedyteller.reporting.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.speedyteller.reporting.api.ReportingApiApplicationTests
import com.speedyteller.reporting.api.config.JwtTokenComponent
import com.speedyteller.reporting.api.config.PostgresContainerSetup
import com.speedyteller.reporting.api.domain.dto.response.GetTransactionResponseDTO
import com.speedyteller.reporting.api.domain.service.TransactionService
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
class TransactionControllerTest {

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var service: TransactionService

    @Autowired
    private lateinit var mockTest: MockTest

    @Autowired
    private lateinit var jwtTokenComponent: JwtTokenComponent

    private var token: String? = null

    @BeforeEach
    fun setup() {

        this.token = jwtTokenComponent.generateAccessToken(User("test", "test", mutableListOf()))
    }

    @Test
    fun `Get Transaction`() {

        val response = mockTest.getTransactionResponse()

        val dtoJSON = mapper.writeValueAsString(GetTransactionResponseDTO(model = response)) as String

        every { service.getTransaction(any()) } returns response

        mockMvc.post("/transaction") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = dtoJSON
            header("Authorization", values = *arrayOf(token!!))
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(dtoJSON) }
        }

    }
}