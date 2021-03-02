package com.speedyteller.reporting.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.speedyteller.reporting.api.ReportingApiApplicationTests
import com.speedyteller.reporting.api.config.JwtTokenComponent
import com.speedyteller.reporting.api.config.PostgresContainerSetup
import com.speedyteller.reporting.api.domain.dto.CustomerDTO
import com.speedyteller.reporting.api.domain.dto.response.GetCustomerResponseDTO
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
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.User
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.client.match.MockRestRequestMatchers.header
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest(classes = [ReportingApiApplicationTests::class])
@ContextConfiguration(initializers = [PostgresContainerSetup::class])
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var service: CustomerService

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
    fun `Test Get Client`() {

        val customer = mockTest.getCustumer()

        val dtoJSON = mapper.writeValueAsString(GetCustomerResponseDTO(customerInfo = CustomerDTO(model = customer))) as String

        every { service.getCustomer(any()) } returns GetCustomerResponse(customerInfo = customer)

        mockMvc.post("/client") {
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
