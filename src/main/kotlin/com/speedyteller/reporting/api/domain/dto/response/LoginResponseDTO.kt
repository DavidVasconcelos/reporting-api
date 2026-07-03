package com.speedyteller.reporting.api.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.speedyteller.reporting.api.domain.model.response.LoginResponse

data class LoginResponseDTO(
    @JsonProperty("access_token")
    val token: String,
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("expires_in")
    val expiresIn: Int,
) {
    constructor(model: LoginResponse) : this(model.token, model.tokenType, model.expiresIn)
}
