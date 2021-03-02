package com.speedyteller.reporting.api.domain.constant

class SecurityConstants {

    companion object {
        const val SECRET = "SECRET_KEY"
        const val  EXPIRATION_TIME = 600_000
        const val  TOKEN_PREFIX = "Bearer "
        const val  HEADER_STRING = "Authorization"
        const val  SIGN_UP_URL = "/api/v3/merchant/user/login"
    }
}