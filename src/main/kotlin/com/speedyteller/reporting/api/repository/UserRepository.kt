package com.speedyteller.reporting.api.repository

import com.speedyteller.reporting.api.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {

    fun findByEmail(email: String) : UserEntity?
}
