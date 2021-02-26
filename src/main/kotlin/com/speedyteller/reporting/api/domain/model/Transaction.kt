package com.speedyteller.reporting.api.domain.model

data class Transaction (

	val id : Int? = null,
	val referenceNo : String? = null,
	val merchantId : Int? = null,
	val status : String? = null,
	val channel : String? = null,
	val customData : String? = null,
	val chainId : String? = null,
	val agentInfoId : Int? = null,
	val operation : String? = null,
	val fxTransactionId : Int? = null,
	val updatedAt : String? = null,
	val createdAt : String? = null,
	val acquirerTransactionId : Int? = null,
	val code : Int? = null,
	val message : String? = null,
	val transactionId : String? = null
)