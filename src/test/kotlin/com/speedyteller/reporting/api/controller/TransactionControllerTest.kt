package com.speedyteller.reporting.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.speedyteller.reporting.api.common.PaginationComponent
import com.speedyteller.reporting.api.domain.dto.request.GetTransactionRequestDTO
import com.speedyteller.reporting.api.domain.dto.response.GetReportDTO
import com.speedyteller.reporting.api.domain.dto.response.GetReportResponseDTO
import com.speedyteller.reporting.api.domain.dto.response.GetTransactionListResponseDTO
import com.speedyteller.reporting.api.domain.dto.response.GetTransactionResponseDTO
import com.speedyteller.reporting.api.domain.service.ReportService
import com.speedyteller.reporting.api.domain.service.TransactionService
import com.speedyteller.reporting.api.mock.MockTest
import com.speedyteller.reporting.api.security.JwtTokenComponent
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.mockk.every
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.core.userdetails.User
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.nio.charset.StandardCharsets
import java.util.Date

@IntegrationTest
@AutoConfigureMockMvc
@Import(ObjectMapper::class)
class TransactionControllerTest {

    @Value("\${caching.spring.loginListTTL}")
    private var jwtExpirationTime: Int = 0

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var service: TransactionService

    @MockkBean
    private lateinit var reportService: ReportService

    @Autowired
    private lateinit var mockTest: MockTest

    @Autowired
    private lateinit var jwtTokenComponent: JwtTokenComponent

    @Autowired
    private lateinit var paginationComponent: PaginationComponent

    private var token: String? = null

    @BeforeAll
    fun setup() {
        this.token = jwtTokenComponent.generateAccessToken(User("test", "test", mutableListOf()))
    }

    @Test
    fun `Successful test get transaction`() {
        val response = mockTest.getTransactionResponse()
        val requestDTOJSON =
            mapper.writeValueAsString(GetTransactionRequestDTO(transactionId = "1-1444392550-1")) as String
        val responseDTOJSON =
            mapper.writeValueAsString(GetTransactionResponseDTO(model = response)) as String

        every { service.getTransaction(any()) } returns response

        mockMvc.post("/transaction") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = requestDTOJSON
            header("Authorization", values = arrayOf(token!!))
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(responseDTOJSON) }
        }
    }

    @Test
    fun `Returns unauthorized when token is not present in the request`() {
        val response = mockTest.getTransactionResponse()
        val dtoJSON =
            mapper.writeValueAsString(GetTransactionResponseDTO(model = response)) as String

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
    fun `Successful test get transaction list`() {
        val page = 1
        val response = mockTest.getTransactionListResponse()
        val listResponseDTO = response.map { GetTransactionListResponseDTO(model = it) }
        val pageDTO = paginationComponent.getPagination(
            pageSize = TransactionController.DEFAULT_PAGE_SIZE,
            page = page,
            uri = "http://localhost/transaction/list?page=$page",
            data = listResponseDTO,
        )
        val dtoJSON = mapper.writeValueAsString(pageDTO) as String

        every { service.getTransactionList(any(), any()) } returns response

        mockMvc.post("/transaction/list?page=$page") {
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
    fun `Returns unauthorized when token is not present in the request to get the list`() {
        val page = 1
        val response = mockTest.getTransactionListResponse()
        val listResponseDTO = response.map { GetTransactionListResponseDTO(model = it) }
        val pageDTO = paginationComponent.getPagination(
            pageSize = TransactionController.DEFAULT_PAGE_SIZE,
            page = page,
            uri = "http://localhost/transaction/list/?page=$page",
            data = listResponseDTO,
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
    fun `Successful test get report`() {
        val response = mockTest.getReportResponse()
        val responseDTO = GetReportResponseDTO(response = response.map { GetReportDTO(model = it) })
        val dtoJSON = mapper.writeValueAsString(responseDTO) as String

        every { reportService.getReport(any()) } returns response

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
    fun `Returns unauthorized when token is not present in the request to get the report`() {
        val response = mockTest.getReportResponse()
        val responseDTO = GetReportResponseDTO(response = response.map { GetReportDTO(model = it) })
        val dtoJSON = mapper.writeValueAsString(responseDTO) as String

        every { reportService.getReport(any()) } returns response

        mockMvc.post("/transaction/report") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = dtoJSON
        }.andExpect {
            status { isUnauthorized() }
        }
    }

    @Test
    fun `Returns unauthorized when token has an invalid issuer`() {
        val badIssuerToken = generateBadIssuerToken()
        val requestDTOJSON = mapper.writeValueAsString(
            GetTransactionRequestDTO(transactionId = "1-1444392550-1"),
        )

        mockMvc.post("/transaction") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = requestDTOJSON
            header("Authorization", values = arrayOf(badIssuerToken))
        }.andExpect {
            status { isUnauthorized() }
        }
    }

    private fun generateBadIssuerToken(): String {
        val secretKey = Keys.hmacShaKeyFor(
            jwtTokenComponent.secret.toByteArray(StandardCharsets.UTF_8),
        )

        return Jwts.builder()
            .subject("test")
            .issuer("Evil-Hacker-Corp")
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + jwtExpirationTime))
            .signWith(secretKey)
            .compact()
    }
}
