package com.speedyteller.reporting.api.repository

import com.speedyteller.reporting.api.domain.entity.TransactionEntity
import com.speedyteller.reporting.api.domain.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<TransactionEntity, Long> {

    fun findByTransactionId(transactionId: String): Transaction?
}