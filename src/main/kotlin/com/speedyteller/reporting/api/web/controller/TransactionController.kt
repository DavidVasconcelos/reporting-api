package com.speedyteller.reporting.api.web.controller

import com.speedyteller.reporting.api.common.PaginationComponent
import com.speedyteller.reporting.api.domain.model.extension.toDTO
import com.speedyteller.reporting.api.domain.model.request.ReportRequest
import com.speedyteller.reporting.api.domain.model.request.TransactionSummaryRequest
import com.speedyteller.reporting.api.domain.service.ReportService
import com.speedyteller.reporting.api.domain.service.TransactionService
import com.speedyteller.reporting.api.web.dto.page.CustomPageDTO
import com.speedyteller.reporting.api.web.dto.request.ReportRequestDTO
import com.speedyteller.reporting.api.web.dto.request.TransactionSummaryRequestDTO
import com.speedyteller.reporting.api.web.dto.response.GetReportDTO
import com.speedyteller.reporting.api.web.dto.response.GetReportResponseDTO
import com.speedyteller.reporting.api.web.dto.response.TransactionResponseDTO
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/transaction")
@Validated
class TransactionController(
    val transactionService: TransactionService,
    val reportService: ReportService,
    val paginationComponent: PaginationComponent,
) {
    @GetMapping
    fun getTransaction(
        @RequestParam(name = "transactionId", required = true) transactionId: String,
    ): ResponseEntity<TransactionResponseDTO> {
        logger.info("Get transaction called for transactionId: $transactionId")
        val transaction =
            transactionService.getTransaction(transactionId = transactionId)
        return ResponseEntity.ok(transaction.toDTO())
    }

    @PostMapping("/list")
    fun getTransactionList(
        @Valid
        @Min(value = 1, message = "Use 1 instead 0 on page")
        @RequestParam(name = "page", defaultValue = "1") page: Int,
        @RequestBody dto: TransactionSummaryRequestDTO,
    ): ResponseEntity<CustomPageDTO> {
        logger.info("Get transaction list request $dto")
        val pageRequest = PageRequest.of(page - 1, DEFAULT_PAGE_SIZE)
        val listOfSummaries = transactionService.getTransactionList(
            request = TransactionSummaryRequest(dto = dto),
            page = pageRequest,
        )
        val listOfResponseDTO = listOfSummaries.map { it.toDTO() }
        val pageDTO = paginationComponent.getPagination(
            pageSize = DEFAULT_PAGE_SIZE,
            page = page,
            uri = getUri(),
            data = listOfResponseDTO,
        )
        return ResponseEntity.ok(pageDTO)
    }

    @PostMapping("/report")
    fun getReport(@RequestBody dto: ReportRequestDTO): ResponseEntity<GetReportResponseDTO> {
        logger.info("Get report request $dto")
        val listOfResponse = reportService.getReport(request = ReportRequest(dto = dto))
        val responseDTO =
            GetReportResponseDTO(response = listOfResponse.map { GetReportDTO(model = it) })
        return ResponseEntity.ok(responseDTO)
    }

    private fun getUri(): String = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .toUriString()

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
        const val DEFAULT_PAGE_SIZE = 50
    }
}
