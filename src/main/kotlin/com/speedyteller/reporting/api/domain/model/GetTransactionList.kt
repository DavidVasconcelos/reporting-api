package com.speedyteller.reporting.api.domain.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class GetTransactionList (

	val originalAmount : BigDecimal? = null,
	val originalCurrency : String? = null,
	val number : String? = null,
	val email : String? = null,
	val billingFirstName : String? = null,
	val billingLastName : String? = null,
	val merchantId : Long? = null,
	val merchantName : String? = null,
	val received : Boolean? = null,
	val referenceNo : String? = null,
	val status : String? = null,
	val operation : String? = null,
	val message : String? = null,
	val created_at : LocalDateTime? = null,
	val transactionId : String? = null,
	val acquirerId : Long? = null,
	val acquirerName : String? = null,
	val acquirerCode : String? = null,
	val acquirerType : String? = null,
	val refundable : Boolean = false
)