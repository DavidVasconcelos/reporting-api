package com.speedyteller.reporting.api.controller

import com.speedyteller.reporting.api.domain.dto.response.GetCustomerResponseDTO
import com.speedyteller.reporting.api.domain.service.CustomerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/client")
class CustomerController(private val customerService: CustomerService) {

    @GetMapping
    fun getClient(
        @RequestParam(name = "transactionId", required = true) transactionId: String,
    ): ResponseEntity<GetCustomerResponseDTO> {
        logger.info("Get client called for transactionId: $transactionId")

        val customerResponse = customerService.getCustomer(transactionId = transactionId)
        return ResponseEntity.ok(GetCustomerResponseDTO(model = customerResponse))
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
