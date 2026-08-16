package com.speedyteller.reporting.api.repository.jpa

import com.speedyteller.reporting.api.domain.entity.MerchantEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MerchantRepository : JpaRepository<MerchantEntity, Long>
