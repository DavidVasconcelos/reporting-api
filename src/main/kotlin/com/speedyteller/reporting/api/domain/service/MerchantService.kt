package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.request.LoginRequest
import com.speedyteller.reporting.api.domain.model.response.LoginResponse

fun interface MerchantService {

    fun login(loginRequest: LoginRequest): LoginResponse
}
