package com.speedyteller.reporting.api.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginResponseDTO(
    @JsonProperty("access_token")
    val token: String,
    @JsonProperty("token_type")
    val tokenType: String = "Bearer",
    @JsonProperty("expires_in")
    val expiresIn: Int,
) : ResponseDTO()
