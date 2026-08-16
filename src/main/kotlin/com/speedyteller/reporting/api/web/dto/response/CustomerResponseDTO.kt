package com.speedyteller.reporting.api.web.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class CustomerResponseDTO(val customerInfo: Customer) {
    data class Customer(
        var id: Long? = null,
        @JsonProperty("created_at")
        var createdAt: String? = null,
        @JsonProperty("updated_at")
        var updatedAt: String? = null,
        @JsonProperty("deleted_at")
        var deletedAt: String? = null,
        var number: String? = null,
        var expiryMonth: String? = null,
        var expiryYear: String? = null,
        var startMonth: String? = null,
        var startYear: String? = null,
        var issueNumber: String? = null,
        var email: String? = null,
        var birthday: String? = null,
        var gender: String? = null,
        var billingTitle: String? = null,
        var billingFirstName: String? = null,
        var billingLastName: String? = null,
        var billingCompany: String? = null,
        var billingAddress1: String? = null,
        var billingAddress2: String? = null,
        var billingCity: String? = null,
        var billingPostcode: String? = null,
        var billingState: String? = null,
        var billingCountry: String? = null,
        var billingPhone: String? = null,
        var billingFax: String? = null,
        var shippingTitle: String? = null,
        var shippingFirstName: String? = null,
        var shippingLastName: String? = null,
        var shippingCompany: String? = null,
        var shippingAddress1: String? = null,
        var shippingAddress2: String? = null,
        var shippingCity: String? = null,
        var shippingPostcode: String? = null,
        var shippingState: String? = null,
        var shippingCountry: String? = null,
        var shippingPhone: String? = null,
        var shippingFax: String? = null,
    )
}
