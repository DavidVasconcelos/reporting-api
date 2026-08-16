package com.speedyteller.reporting.api.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.speedyteller.reporting.api.common.PaginationComponent
import com.speedyteller.reporting.api.domain.model.extension.toDTO
import com.speedyteller.reporting.api.domain.service.ReportService
import com.speedyteller.reporting.api.domain.service.TransactionService
import com.speedyteller.reporting.api.mock.MockTest
import com.speedyteller.reporting.api.security.JwtTokenComponent
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import com.speedyteller.reporting.api.support.annotations.andResultBodyMatches
import com.speedyteller.reporting.api.web.dto.request.GetReportRequestDTO
import com.speedyteller.reporting.api.web.dto.request.GetTransactionListRequestDTO
import com.speedyteller.reporting.api.web.dto.response.GetReportDTO
import com.speedyteller.reporting.api.web.dto.response.GetReportResponseDTO
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
import org.springframework.security.core.authority.SimpleGrantedAuthority
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
        val mockUser = User(
            "test",
            "test",
            mutableListOf(SimpleGrantedAuthority("session:role-any")),
        )
        this.jwtToken = jwtTokenComponent.generateAccessToken(mockUser)
    }

    @Test
    fun `Successful test get transaction`() {
        val transaction = mockTest.getTransaction()
        val transactionId = "1-1444392550-1"
        val expectedTransaction =
            mapper.writeValueAsString(transaction.toDTO()) as String

        every { service.getTransaction(any()) } returns transaction

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
    fun `Returns forbidden when token is not present in the request`() {
        val response = mockTest.getTransaction()
        val transactionId = "1-1444392550-1"

        every { service.getTransaction(any()) } returns response

        mockMvc.perform(
            MockMvcRequestBuilders.get("/transaction?transactionId=$transactionId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON),
        ).andExpect(
            MockMvcResultMatchers.status().isForbidden,
        )
    }

    @Test
    fun `Returns forbidden when token has an invalid issuer`() {
        val badIssuerToken = generateBadIssuerToken()
        val transactionId = "1-1444392550-1"

        mockMvc.perform(
            MockMvcRequestBuilders.get("/transaction?transactionId=$transactionId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", badIssuerToken),
        ).andExpect(
            MockMvcResultMatchers.status().isForbidden,
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
    fun `Successful test get transaction summary`() {
        val page = 1
        val request = mapper.writeValueAsString(GetTransactionListRequestDTO())
        val listOfSummaries = mockTest.getTransactionSummaryList()
        val listResponseDTO = listOfSummaries.map { it.toDTO() }
        val pageDTO = paginationComponent.getPagination(
            pageSize = TransactionController.DEFAULT_PAGE_SIZE,
            page = page,
            uri = "http://localhost/transaction/list?page=$page",
            data = listResponseDTO,
        )
        val expectedTransactionList = mapper.writeValueAsString(pageDTO) as String

        every { service.getTransactionList(any(), any()) } returns listOfSummaries

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
    fun `Returns forbidden when token is not present in the request to get the summary`() {
        val page = 1
        val listOfSummaries = mockTest.getTransactionSummaryList()
        val listResponseDTO = listOfSummaries.map { it.toDTO() }
        val pageDTO = paginationComponent.getPagination(
            pageSize = TransactionController.DEFAULT_PAGE_SIZE,
            page = page,
            uri = "http://localhost/transaction/list/?page=$page",
            data = listResponseDTO,
        )
        val dtoJSON = mapper.writeValueAsString(pageDTO) as String

        every { service.getTransactionList(any(), any()) } returns listOfSummaries

        mockMvc.perform(
            MockMvcRequestBuilders.post("/transaction/list/?page=$page")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(dtoJSON),
        ).andExpect(
            MockMvcResultMatchers.status().isForbidden,
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
    fun `Returns forbidden when token is not present in the request to get the report`() {
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
            MockMvcResultMatchers.status().isForbidden,
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
