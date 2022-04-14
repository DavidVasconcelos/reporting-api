package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.service.UserService
import com.speedyteller.reporting.api.port.PostgresPort
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val postgresPort: PostgresPort) : UserService {

    override fun findByEmail(email: String) = postgresPort.getUser(email = email)
}
