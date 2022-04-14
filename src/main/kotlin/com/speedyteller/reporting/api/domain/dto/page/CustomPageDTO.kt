package com.speedyteller.reporting.api.domain.dto.page

data class CustomPageDTO(

    val perPage: Int? = null,
    val currentPage: Int? = null,
    val nextPageUrl: String? = null,
    val prevPageUrl: String? = null,
    val from: Int? = null,
    val to: Int? = null,
    val data: List<Any>? = null
)
