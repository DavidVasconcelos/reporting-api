package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.Login
import com.speedyteller.reporting.api.domain.model.request.LoginRequest

fun interface MerchantService {

    fun login(loginRequest: LoginRequest): Login
}
