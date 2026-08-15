package com.speedyteller.reporting.api.repository.jpa

import com.speedyteller.reporting.api.domain.entity.FXTransactionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FXTransactionRepository : JpaRepository<FXTransactionEntity, Long>
