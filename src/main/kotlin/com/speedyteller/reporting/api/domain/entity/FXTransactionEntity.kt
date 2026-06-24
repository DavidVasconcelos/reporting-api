package com.speedyteller.reporting.api.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "fx_transaction")
class FXTransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fx_transaction_id_seq")
    @SequenceGenerator(name = "fx_transaction_id_seq", sequenceName = "fx_transaction_id_seq", allocationSize = 1)
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "original_amount")
    val originalAmount: BigDecimal? = null,
    @Column(name = "original_currency")
    val originalCurrency: String? = null,
)
