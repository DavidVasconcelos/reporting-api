package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.User

fun interface UserService {

    fun findByEmail(email: String): User
}
