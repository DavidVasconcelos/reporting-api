package com.speedyteller.reporting.api.domain.entity

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "fx_transaction")
data class FXTransactionEntity(
    @Id
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "original_amount")
    val originalAmount: BigDecimal? = null,
    @Column(name = "original_currency")
    val originalCurrency: String? = null
)
