package com.speedyteller.reporting.api.repository

import com.speedyteller.reporting.api.domain.entity.AgentInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AgentInfoRepository : JpaRepository<AgentInfoEntity, Long>