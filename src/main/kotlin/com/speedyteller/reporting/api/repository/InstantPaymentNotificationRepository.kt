package com.speedyteller.reporting.api.repository

import com.speedyteller.reporting.api.domain.entity.InstantPaymentNotificationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface InstantPaymentNotificationRepository : JpaRepository<InstantPaymentNotificationEntity, Long>
