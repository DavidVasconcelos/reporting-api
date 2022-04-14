package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.usecase.FindUserByEmail
import org.springframework.stereotype.Service

@Service
class UserService(val findUserByEmail: FindUserByEmail) {

    fun findByEmail(email: String) = findUserByEmail.handle(email = email)
}
