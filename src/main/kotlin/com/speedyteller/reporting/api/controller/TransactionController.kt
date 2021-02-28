package com.speedyteller.reporting.api.controller

import com.speedyteller.reporting.api.domain.dto.CustomerDTO
import com.speedyteller.reporting.api.domain.dto.GetTransactionResponseDTO
import com.speedyteller.reporting.api.domain.service.TransactionService
import com.speedyteller.reporting.api.exception.ErrorResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/transaction")
@Api(value = "Transaction", description = "Transaction REST API")
class TransactionController {

    @Autowired
    private lateinit var transactionService: TransactionService

    @ApiOperation(httpMethod = "POST", value = "Get Transaction")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "When call has be succeeded", response = GetTransactionResponseDTO::class),
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
}
