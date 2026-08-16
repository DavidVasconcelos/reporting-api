package com.speedyteller.reporting.api.web.controller

import com.speedyteller.reporting.api.domain.model.extension.toDTO
import com.speedyteller.reporting.api.domain.service.CustomerService
import com.speedyteller.reporting.api.web.dto.response.CustomerResponseDTO
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
    ): ResponseEntity<CustomerResponseDTO> {
        logger.info("Get client called for transactionId: $transactionId")

        val model = customerService.getCustomer(transactionId = transactionId)
        return ResponseEntity.ok(model.toDTO())
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
