package com.speedyteller.reporting.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.speedyteller.reporting.api.config.JwtTokenComponent
import com.speedyteller.reporting.api.domain.dto.CustomerDTO
import com.speedyteller.reporting.api.domain.dto.request.GetTransactionRequestDTO
import com.speedyteller.reporting.api.domain.dto.response.GetCustomerResponseDTO
import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.domain.service.CustomerService
import com.speedyteller.reporting.api.mock.MockTest
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.security.core.userdetails.User
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@IntegrationTest
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

        val requestDTOJSON =
            mapper.writeValueAsString(GetTransactionRequestDTO(transactionId = "1-1444392550-1")) as String

        val dtoJSON =
            mapper.writeValueAsString(GetCustomerResponseDTO(customerInfo = CustomerDTO(model = customer))) as String

        every { service.getCustomer(any()) } returns GetCustomerResponse(customerInfo = customer)

        mockMvc.post("/client") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = requestDTOJSON
            header("Authorization", values = arrayOf(token!!))
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(dtoJSON) }
        }
    }

    @Test
    fun `Test Get Client Unauthorized`() {

        val customer = mockTest.getCustumer()

        val dtoJSON =
            mapper.writeValueAsString(GetCustomerResponseDTO(customerInfo = CustomerDTO(model = customer))) as String

        every { service.getCustomer(any()) } returns GetCustomerResponse(customerInfo = customer)

        mockMvc.post("/client") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = dtoJSON
        }.andExpect {
            status { isUnauthorized() }
        }
    }
}
