package com.speedyteller.reporting.api.controller

import com.speedyteller.reporting.api.domain.dto.response.GetCustomerResponseDTO
import com.speedyteller.reporting.api.domain.service.CustomerService
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
@RequestMapping("/client")
@Api(value = "Client", description = "Client REST API")
class CustomerController {

    @Autowired
    private lateinit var customerService: CustomerService

    @ApiOperation(httpMethod = "POST", value = "Get Client")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "When call has be succeeded", response = GetCustomerResponseDTO::class),
            ApiResponse(
                code = 404, message = "Transaction not found",
                response = ErrorResponse::class
            ),
            ApiResponse(
                code = 404, message = "Customer not found",
                response = ErrorResponse::class
            ),
            ApiResponse(
                code = 404, message = "Customer id not present on transaction",
                response = ErrorResponse::class
            )
        ]
    )
    @PostMapping
    fun getClient(@Valid @RequestBody transactionId: String): ResponseEntity<GetCustomerResponseDTO> {

        val customerResponse = customerService.getCustomer(transactionId = transactionId)

        return ResponseEntity.ok(GetCustomerResponseDTO(model = customerResponse))
    }
}
