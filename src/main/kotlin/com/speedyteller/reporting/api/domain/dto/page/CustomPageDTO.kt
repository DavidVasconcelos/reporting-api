package com.speedyteller.reporting.api.domain.dto.page

data class CustomPageDTO(

    val per_page: Int? = null,
    val current_page: Int? = null,
    val next_page_url: String? = null,
    val prev_page_url: String? = null,
    val from: Int? = null,
    val to: Int? = null,
    val data: List<Any>? = null
)
