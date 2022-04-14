package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.User

interface UserService {

    fun findByEmail(email: String): User
}
