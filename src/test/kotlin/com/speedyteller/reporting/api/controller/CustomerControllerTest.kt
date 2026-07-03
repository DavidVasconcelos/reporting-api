package com.speedyteller.reporting.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.speedyteller.reporting.api.domain.dto.response.GetCustomerResponseDTO
import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.domain.service.CustomerService
import com.speedyteller.reporting.api.mock.MockTest
import com.speedyteller.reporting.api.security.JwtTokenComponent
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import com.speedyteller.reporting.api.support.annotations.andResultBodyMatches
import io.mockk.every
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.core.userdetails.User
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@IntegrationTest
@AutoConfigureMockMvc
@Import(ObjectMapper::class)
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

    private lateinit var jwtToken: String

    @BeforeAll
    fun setup() {
        this.jwtToken = jwtTokenComponent.generateAccessToken(User("test", "test", mutableListOf()))
    }

    @Test
    fun `Successful test get client`() {
        val customer = mockTest.getCustumer()
        val transactionId = "1-1444392550-1"
        val expectedCustomer =
            mapper.writeValueAsString(GetCustomerResponseDTO(GetCustomerResponse(customerInfo = customer))) as String

        every { service.getCustomer(any()) } returns GetCustomerResponse(customerInfo = customer)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/client?transactionId=$transactionId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", jwtToken),
        ).andExpect(
            MockMvcResultMatchers.status().isOk,
        ).andResultBodyMatches(json = expectedCustomer)
    }

    @Test
    fun `Returns unauthorized when token is not present in the request`() {
        val customer = mockTest.getCustumer()
        val transactionId = "1-1444392550-1"

        every { service.getCustomer(any()) } returns GetCustomerResponse(customerInfo = customer)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/client?transactionId=$transactionId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON),
        ).andExpect(
            MockMvcResultMatchers.status().isUnauthorized,
        )
    }

    @Test
    fun `Returns bad request when transaction is provided`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", jwtToken),
        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest,
        )
    }
}
