package com.speedyteller.reporting.api.repository

import com.speedyteller.reporting.api.domain.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<CustomerEntity, Long>
