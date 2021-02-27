package com.speedyteller.reporting.api.domain.model

import java.time.LocalDateTime

data class Customer (

	val id : Long? = null,
	val createdAt : LocalDateTime? = null,
	val updatedAt : LocalDateTime? = null,
	val deletedAt : LocalDateTime? = null,
	val number : String? = null,
	val expiryMonth : String? = null,
	val expiryYear : String? = null,
	val startMonth : String? = null,
	val startYear : String? = null,
	val issueNumber : String? = null,
	val email : String? = null,
	val birthday : LocalDateTime? = null,
	val gender : String? = null,
	val billingTitle : String? = null,
	val billingFirstName : String? = null,
	val billingLastName : String? = null,
	val billingCompany : String? = null,
	val billingAddress1 : String? = null,
	val billingAddress2 : String? = null,
	val billingCity : String? = null,
	val billingPostcode : String? = null,
	val billingState : String? = null,
	val billingCountry : String? = null,
	val billingPhone : String? = null,
	val billingFax : String? = null,
	val shippingTitle : String? = null,
	val shippingFirstName : String? = null,
	val shippingLastName : String? = null,
	val shippingCompany : String? = null,
	val shippingAddress1 : String? = null,
	val shippingAddress2 : String? = null,
	val shippingCity : String? = null,
	val shippingPostcode : String? = null,
	val shippingState : String? = null,
	val shippingCountry : String? = null,
	val shippingPhone : String? = null,
	val shippingFax : String? = null
)
