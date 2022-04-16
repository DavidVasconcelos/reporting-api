package com.speedyteller.reporting.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.speedyteller.reporting.api.domain.dto.request.LoginRequestDTO
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@IntegrationTest
@AutoConfigureMockMvc
class MerchantControllerTest {

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `Test Login`() {

        val requestDTOJSON = mapper.writeValueAsString(LoginRequestDTO(email = "test", password = "teste")) as String

        mockMvc.post("/merchant/user/login") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = requestDTOJSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }
    }

    @Test
    fun `Test Login Unauthorized`() {

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
