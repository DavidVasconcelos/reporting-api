package com.speedyteller.reporting.api.domain.model.extension

import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.extension.toStringPattern
import com.speedyteller.reporting.api.web.dto.response.CustomerResponseDTO

fun Customer.toDTO(): CustomerResponseDTO = CustomerResponseDTO(
    customerInfo = CustomerResponseDTO.Customer().apply {
        this.id = this@toDTO.id
        this.createdAt = this@toDTO.createdAt?.toStringPattern()
        this.updatedAt = this@toDTO.updatedAt?.toStringPattern()
        this.deletedAt = this@toDTO.deletedAt?.toStringPattern()
        this.number = this@toDTO.number
        this.expiryMonth = this@toDTO.expiryMonth
        this.expiryYear = this@toDTO.expiryYear
        this.startMonth = this@toDTO.startMonth
        this.startYear = this@toDTO.startYear
        this.issueNumber = this@toDTO.issueNumber
        this.email = this@toDTO.email
        this.birthday = this@toDTO.birthday?.toStringPattern()
        this.gender = this@toDTO.gender
        this.billingTitle = this@toDTO.billingTitle
        this.billingFirstName = this@toDTO.billingFirstName
        this.billingLastName = this@toDTO.billingLastName
        this.billingCompany = this@toDTO.billingCompany
        this.billingAddress1 = this@toDTO.billingAddress1
        this.billingAddress2 = this@toDTO.billingAddress2
        this.billingCity = this@toDTO.billingCity
        this.billingPostcode = this@toDTO.billingPostcode
        this.billingState = this@toDTO.billingState
        this.billingCountry = this@toDTO.billingCountry
        this.billingPhone = this@toDTO.billingPhone
        this.billingFax = this@toDTO.billingFax
        this.shippingTitle = this@toDTO.shippingTitle
        this.shippingFirstName = this@toDTO.shippingFirstName
        this.shippingLastName = this@toDTO.shippingLastName
        this.shippingCompany = this@toDTO.shippingCompany
        this.shippingAddress1 = this@toDTO.shippingAddress1
        this.shippingAddress2 = this@toDTO.shippingAddress2
        this.shippingCity = this@toDTO.shippingCity
        this.shippingPostcode = this@toDTO.shippingPostcode
        this.shippingState = this@toDTO.shippingState
        this.shippingCountry = this@toDTO.shippingCountry
        this.shippingPhone = this@toDTO.shippingPhone
        this.shippingFax = this@toDTO.shippingFax
    },
)
