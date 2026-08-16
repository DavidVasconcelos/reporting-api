package com.speedyteller.reporting.api.repository.jpa

import com.speedyteller.reporting.api.domain.entity.AcquirerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AcquirerRepository : JpaRepository<AcquirerEntity, Long>
