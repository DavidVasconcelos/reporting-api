package com.speedyteller.reporting.api.repository

import com.speedyteller.reporting.api.domain.entity.AcquirerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AcquirerRepository : JpaRepository<AcquirerEntity, Long>