package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.service.UserService
import com.speedyteller.reporting.api.domain.usecase.FindUserByEmail
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val findUserByEmail: FindUserByEmail) : UserService {

    override fun findByEmail(email: String) = findUserByEmail.handle(email = email)
}
