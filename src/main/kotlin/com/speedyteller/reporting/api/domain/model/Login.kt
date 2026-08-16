package com.speedyteller.reporting.api.domain.model

data class Login(val token: String, val tokenType: String = "Bearer", val expiresIn: Int)
