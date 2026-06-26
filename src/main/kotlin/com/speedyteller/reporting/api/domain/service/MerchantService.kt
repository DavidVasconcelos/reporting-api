package com.speedyteller.reporting.api.domain.service

import org.springframework.security.core.userdetails.User

fun interface MerchantService {

    fun login(user: User): String
}
