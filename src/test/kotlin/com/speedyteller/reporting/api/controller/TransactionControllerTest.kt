package com.speedyteller.reporting.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.speedyteller.reporting.api.common.PaginationComponent
import com.speedyteller.reporting.api.domain.dto.request.GetReportRequestDTO
import com.speedyteller.reporting.api.domain.dto.request.GetTransactionListRequestDTO
import com.speedyteller.reporting.api.domain.dto.response.GetReportDTO
import com.speedyteller.reporting.api.domain.dto.response.GetReportResponseDTO
import com.speedyteller.reporting.api.domain.dto.response.GetTransactionListResponseDTO
import com.speedyteller.reporting.api.domain.dto.response.GetTransactionResponseDTO
import com.speedyteller.reporting.api.domain.service.ReportService
import com.speedyteller.reporting.api.domain.service.TransactionService
import com.speedyteller.reporting.api.mock.MockTest
import com.speedyteller.reporting.api.security.JwtTokenComponent
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import com.speedyteller.reporting.api.support.annotations.andResultBodyMatches
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.nio.charset.StandardCharsets
import java.util.Date

@IntegrationTest
@AutoConfigureMockMvc
@Import(ObjectMapper::class)
class TransactionControllerTest {

    @Value("\${security.jwt-expiration-time}")
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

    private lateinit var jwtToken: String

    @BeforeAll
    fun setup() {
        this.jwtToken = jwtTokenComponent.generateAccessToken(User("test", "test", mutableListOf()))
    }

    @Test
    fun `Successful test get transaction`() {
        val response = mockTest.getTransactionResponse()
        val transactionId = "1-1444392550-1"
        val expectedTransaction =
            mapper.writeValueAsString(GetTransactionResponseDTO(model = response)) as String

        every { service.getTransaction(any()) } returns response

        mockMvc.perform(
            MockMvcRequestBuilders.get("/transaction?transactionId=$transactionId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", jwtToken),
        ).andExpect(
            MockMvcResultMatchers.status().isOk,
        ).andResultBodyMatches(json = expectedTransaction)
    }

    @Test
    fun `Returns unauthorized when token is not present in the request`() {
        val response = mockTest.getTransactionResponse()
        val transactionId = "1-1444392550-1"

        every { service.getTransaction(any()) } returns response

        mockMvc.perform(
            MockMvcRequestBuilders.get("/transaction?transactionId=$transactionId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON),
        ).andExpect(
            MockMvcResultMatchers.status().isUnauthorized,
        )
    }

    @Test
    fun `Returns unauthorized when token has an invalid issuer`() {
        val badIssuerToken = generateBadIssuerToken()
        val transactionId = "1-1444392550-1"

        mockMvc.perform(
            MockMvcRequestBuilders.get("/transaction?transactionId=$transactionId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", badIssuerToken),
        ).andExpect(
            MockMvcResultMatchers.status().isUnauthorized,
        )
    }

    @Test
    fun `Returns bad request when transaction is provided`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", jwtToken),
        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest,
        )
    }

    @Test
    fun `Successful test get transaction list`() {
        val page = 1
        val request = mapper.writeValueAsString(GetTransactionListRequestDTO())
        val response = mockTest.getTransactionListResponse()
        val listResponseDTO = response.map { GetTransactionListResponseDTO(model = it) }
        val pageDTO = paginationComponent.getPagination(
            pageSize = TransactionController.DEFAULT_PAGE_SIZE,
            page = page,
            uri = "http://localhost/transaction/list?page=$page",
            data = listResponseDTO,
        )
        val expectedTransactionList = mapper.writeValueAsString(pageDTO) as String

        every { service.getTransactionList(any(), any()) } returns response

        mockMvc.perform(
            MockMvcRequestBuilders.post("/transaction/list?page=$page")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", jwtToken)
                .content(request),
        ).andExpect(
            MockMvcResultMatchers.status().isOk,
        ).andResultBodyMatches(json = expectedTransactionList)
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

        mockMvc.perform(
            MockMvcRequestBuilders.post("/transaction/list/?page=$page")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(dtoJSON),
        ).andExpect(
            MockMvcResultMatchers.status().isUnauthorized,
        )
    }

    @Test
    fun `Successful test get report`() {
        val request = mapper.writeValueAsString(GetReportRequestDTO())
        val response = mockTest.getReportResponse()
        val responseDTO = GetReportResponseDTO(response = response.map { GetReportDTO(model = it) })
        val expectedReport = mapper.writeValueAsString(responseDTO) as String

        every { reportService.getReport(any()) } returns response

        mockMvc.perform(
            MockMvcRequestBuilders.post("/transaction/report")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", jwtToken)
                .content(request),
        ).andExpect(
            MockMvcResultMatchers.status().isOk,
        ).andResultBodyMatches(json = expectedReport)
    }

    @Test
    fun `Returns unauthorized when token is not present in the request to get the report`() {
        val response = mockTest.getReportResponse()
        val responseDTO = GetReportResponseDTO(response = response.map { GetReportDTO(model = it) })
        val dtoJSON = mapper.writeValueAsString(responseDTO) as String

        every { reportService.getReport(any()) } returns response

        mockMvc.perform(
            MockMvcRequestBuilders.post("/transaction/report")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(dtoJSON),
        ).andExpect(
            MockMvcResultMatchers.status().isUnauthorized,
        )
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
