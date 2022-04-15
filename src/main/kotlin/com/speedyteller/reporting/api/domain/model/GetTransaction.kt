package com.speedyteller.reporting.api.domain.model

data class GetTransaction(
    val fx: FXTransaction,
    val customerInfo: Customer,
    val acquirer: Acquirer,
    val merchant: Merchant,
    val transaction: Transaction
)
