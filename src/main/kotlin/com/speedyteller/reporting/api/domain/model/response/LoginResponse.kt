package com.speedyteller.reporting.api.domain.model.response

data class LoginResponse(val token: String, val tokenType: String = "Bearer", val expiresIn: Int)
