package com.speedyteller.reporting.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.speedyteller.reporting.api.ReportingApiApplicationTests
import com.speedyteller.reporting.api.config.JwtTokenComponent
import com.speedyteller.reporting.api.config.PaginationComponent
import com.speedyteller.reporting.api.config.PostgresContainerSetup
import com.speedyteller.reporting.api.domain.dto.response.GetReportDTO
import com.speedyteller.reporting.api.domain.dto.response.GetReportResponseDTO
import com.speedyteller.reporting.api.domain.dto.response.GetTransactionListResponseDTO
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

    @Autowired
    private lateinit var paginationComponent: PaginationComponent

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
            header("Authorization", values = arrayOf(token!!))
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(dtoJSON) }
        }

    }

    @Test
    fun `Get Transaction Unauthorized`() {

        val response = mockTest.getTransactionResponse()

        val dtoJSON = mapper.writeValueAsString(GetTransactionResponseDTO(model = response)) as String

        every { service.getTransaction(any()) } returns response

        mockMvc.post("/transaction") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = dtoJSON
        }.andExpect {
            status { isUnauthorized() }
        }

    }

    @Test
    fun `Get Transaction List`() {

        val page = 1

        val response = mockTest.getTransactionListResponse()

        val listResponseDTO = response.map { GetTransactionListResponseDTO(model = it) }

        val pageDTO = paginationComponent.getPagination(
            pageSize = TransactionController.DEFAULT_PAGE_SIZE,
            page = page,
            uri = "http://localhost/transaction/list/?page=$page",
            data = listResponseDTO
        )

        val dtoJSON = mapper.writeValueAsString(pageDTO) as String

        every { service.getTransactionList(any(), any()) } returns response

        mockMvc.post("/transaction/list/?page=$page") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = dtoJSON
            header("Authorization", values = arrayOf(token!!))
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(dtoJSON) }
        }

    }

    @Test
    fun `Get Transaction List Unauthorized`() {

        val page = 1

        val response = mockTest.getTransactionListResponse()

        val listResponseDTO = response.map { GetTransactionListResponseDTO(model = it) }

        val pageDTO = paginationComponent.getPagination(
            pageSize = TransactionController.DEFAULT_PAGE_SIZE,
            page = page,
            uri = "http://localhost/transaction/list/?page=$page",
            data = listResponseDTO
        )

        val dtoJSON = mapper.writeValueAsString(pageDTO) as String

        every { service.getTransactionList(any(), any()) } returns response

        mockMvc.post("/transaction/list/?page=$page") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = dtoJSON
        }.andExpect {
            status { isUnauthorized() }
        }

    }

    @Test
    fun `Get Report`() {

        val response = mockTest.getReportResponse()

        val responseDTO = GetReportResponseDTO(response = response.map { GetReportDTO(model = it) })

        val dtoJSON = mapper.writeValueAsString(responseDTO) as String

        every { service.getReport(any()) } returns response

        mockMvc.post("/transaction/report") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = dtoJSON
            header("Authorization", values = arrayOf(token!!))
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(dtoJSON) }
        }
    }

    @Test
    fun `Get Report Unauthorized`() {

        val response = mockTest.getReportResponse()

        val responseDTO = GetReportResponseDTO(response = response.map { GetReportDTO(model = it) })

        val dtoJSON = mapper.writeValueAsString(responseDTO) as String

        every { service.getReport(any()) } returns response

        mockMvc.post("/transaction/report") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = dtoJSON
        }.andExpect {
            status { isUnauthorized() }
        }
    }
}
