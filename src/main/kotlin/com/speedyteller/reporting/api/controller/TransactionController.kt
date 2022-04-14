package com.speedyteller.reporting.api.controller

import com.speedyteller.reporting.api.config.PaginationComponent
import com.speedyteller.reporting.api.domain.dto.page.CustomPageDTO
import com.speedyteller.reporting.api.domain.dto.request.GetReportRequestDTO
import com.speedyteller.reporting.api.domain.dto.request.GetTransactionListRequestDTO
import com.speedyteller.reporting.api.domain.dto.response.GetReportDTO
import com.speedyteller.reporting.api.domain.dto.response.GetReportResponseDTO
import com.speedyteller.reporting.api.domain.dto.response.GetTransactionListResponseDTO
import com.speedyteller.reporting.api.domain.dto.response.GetTransactionResponseDTO
import com.speedyteller.reporting.api.domain.model.request.GetReportRequest
import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.domain.service.TransactionService
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid
import javax.validation.constraints.Min

@RestController
@RequestMapping("/transaction")
@Validated
class TransactionController(val transactionService: TransactionService, val paginationComponent: PaginationComponent) {
    @PostMapping
    fun getTransaction(@Valid @RequestBody transactionId: String): ResponseEntity<GetTransactionResponseDTO> {
        val transactionResponse = transactionService.getTransaction(transactionId = transactionId)
        return ResponseEntity.ok(GetTransactionResponseDTO(model = transactionResponse))
    }

    @PostMapping("/list")
    fun getTransactionList(
        @Valid
        @Min(value = 1, message = "Use 1 instead 0 on page")
        @RequestParam(defaultValue = "1") page: Int,
        @RequestBody dto: GetTransactionListRequestDTO
    ): ResponseEntity<CustomPageDTO> {
        val pageRequest = PageRequest.of(page, DEFAULT_PAGE_SIZE)
        val listOfResponse =
            transactionService.getTransactionList(request = GetTransactionListRequest(dto = dto), page = pageRequest)
        val listOfResponseDTO = listOfResponse.map { GetTransactionListResponseDTO(model = it) }
        val pageDTO = paginationComponent.getPagination(
            pageSize = DEFAULT_PAGE_SIZE,
            page = page,
            uri = getUri(),
            data = listOfResponseDTO
        )
        return ResponseEntity.ok(pageDTO)
    }
    @PostMapping("/report")
    fun getReport(@RequestBody dto: GetReportRequestDTO): ResponseEntity<GetReportResponseDTO> {
        val listOfResponse = transactionService.getReport(request = GetReportRequest(dto = dto))
        val responseDTO = GetReportResponseDTO(response = listOfResponse.map { GetReportDTO(model = it) })
        return ResponseEntity.ok(responseDTO)
    }
    private fun getUri(): String {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .toUriString()
    }
    companion object {
        const val DEFAULT_PAGE_SIZE = 50
    }
}
