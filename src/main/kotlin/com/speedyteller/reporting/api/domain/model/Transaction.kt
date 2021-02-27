package com.speedyteller.reporting.api.domain.model

data class Transaction (

	val id : Long? = null,
	val referenceNo : String? = null,
	val merchantId : Long? = null,
	val status : String? = null,
	val channel : String? = null,
	val customData : String? = null,
	val chainId : String? = null,
	val agentInfoId : Long? = null,
	val operation : String? = null,
	val fxTransactionId : Long? = null,
	val updatedAt : String? = null,
	val createdAt : String? = null,
	val acquirerTransactionId : Long? = null,
	val code : Int? = null,
	val message : String? = null,
	val transactionId : String? = null,
	val customerId: Long? = null
)