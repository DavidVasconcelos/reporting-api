package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.User
import com.speedyteller.reporting.api.domain.service.UserService
import com.speedyteller.reporting.api.port.PostgresPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var postgresPort: PostgresPort

    override fun findByEmail(email: String): User {

        return postgresPort.getUser(email = email)
    }
}
