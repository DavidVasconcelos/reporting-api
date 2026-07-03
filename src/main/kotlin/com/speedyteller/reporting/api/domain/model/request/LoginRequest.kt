package com.speedyteller.reporting.api.domain.model.request

import com.speedyteller.reporting.api.domain.dto.request.LoginRequestDTO

data class LoginRequest(val email: String, val password: String) {
    constructor(dto: LoginRequestDTO) : this(dto.email, dto.password)
}
