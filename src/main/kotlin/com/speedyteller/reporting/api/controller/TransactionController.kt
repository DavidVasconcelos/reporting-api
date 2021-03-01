package com.speedyteller.reporting.api.controller

import com.speedyteller.reporting.api.domain.dto.page.CustomPageDTO
import com.speedyteller.reporting.api.domain.dto.request.GetTransactionListRequestDTO
import com.speedyteller.reporting.api.domain.dto.response.GetTransactionListResponseDTO
import com.speedyteller.reporting.api.domain.dto.response.GetTransactionResponseDTO
import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.domain.service.TransactionService
import com.speedyteller.reporting.api.exception.ErrorResponse
import com.speedyteller.reporting.api.repository.impl.TransactionRepositoryImpl
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid
import javax.validation.constraints.Min

@RestController
@RequestMapping("/transaction")
@Api(value = "Transaction", description = "Transaction REST API")
@Validated
class TransactionController {

    @Autowired
    private lateinit var transactionService: TransactionService

    @ApiOperation(httpMethod = "POST", value = "Get Transaction")
    @ApiResponses(
        value = [
            ApiResponse(
                code = 200,
                message = "When call has be succeeded",
                response = GetTransactionResponseDTO::class
            ),
            ApiResponse(
                code = 404, message = "Transaction not found",
                response = ErrorResponse::class
            )
        ]
    )
    @PostMapping
    fun getTransaction(@Valid @RequestBody transactionId: String): ResponseEntity<GetTransactionResponseDTO> {

        val transactionResponse = transactionService.getTransaction(transactionId = transactionId)

        return ResponseEntity.ok(GetTransactionResponseDTO(model = transactionResponse))
    }

    @ApiOperation(httpMethod = "POST", value = "Transaction Query")
    @ApiResponses(
        value = [
            ApiResponse(
                code = 200,
                message = "When call has be succeeded",
                response = GetTransactionResponseDTO::class,
                responseContainer = "List"
            ),
            ApiResponse(
                code = 404, message = "Transaction not found",
                response = ErrorResponse::class
            )
        ]
    )
    @PostMapping("/list")
    fun getTransactionList(
        @Valid
        @Min(value = 1, message = "Use 1 instead 0 on page")
        @RequestParam(defaultValue = "1") page: Int,
        @RequestBody dto: GetTransactionListRequestDTO
    ): ResponseEntity<CustomPageDTO> {

        val pageRequest = PageRequest.of(page, DEAFULT_PAGE_SIZE)

        val listOfResonse =
            transactionService.getTransactionList(request = GetTransactionListRequest(dto = dto), page = pageRequest)

        val listResponseDTO = listOfResonse.map { GetTransactionListResponseDTO(model = it) }

        val pageDTO = CustomPageDTO(
            per_page = DEAFULT_PAGE_SIZE,
            current_page = page,
            next_page_url = getUri(page.plus(ONE)),
            prev_page_url = if (page > ONE) getUri(page.minus(ONE)) else null,
            from = (page).times(DEAFULT_PAGE_SIZE),
            to = listResponseDTO.count().plus((page).times(DEAFULT_PAGE_SIZE)),
            data = listResponseDTO
        )

        return ResponseEntity.ok(pageDTO)
    }

    private fun getUri(page: Int): String {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/?page={page}")
            .buildAndExpand(page).toUriString()
    }

    companion object {
        const val DEAFULT_PAGE_SIZE = 1
        const val ONE = 1
    }
}
