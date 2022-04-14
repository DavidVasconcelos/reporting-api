package com.speedyteller.reporting.api.controller

import com.speedyteller.reporting.api.domain.dto.response.GetCustomerResponseDTO
import com.speedyteller.reporting.api.domain.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/client")
class CustomerController(val customerService: CustomerService) {

    @PostMapping
    fun getClient(@Valid @RequestBody transactionId: String): ResponseEntity<GetCustomerResponseDTO> {
        val customerResponse = customerService.getCustomer(transactionId = transactionId)
        return ResponseEntity.ok(GetCustomerResponseDTO(model = customerResponse))
    }
}
