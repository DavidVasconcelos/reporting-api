package com.speedyteller.reporting.api.repository

import com.speedyteller.reporting.api.domain.entity.TransactionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TransactionRepository :
    JpaRepository<TransactionEntity, Long>,
    CustomRepository {

    @Query(
        """
        SELECT t FROM TransactionEntity t
        LEFT JOIN FETCH t.fxTransaction
        LEFT JOIN FETCH t.customer
        LEFT JOIN FETCH t.acquirer
        LEFT JOIN FETCH t.merchant
        LEFT JOIN FETCH t.agentInfo
        WHERE t.transactionId = :transactionId
    """,
    )
    fun findByTransactionId(transactionId: String): TransactionEntity?
}
