package com.speedyteller.reporting.api.repository

import org.springframework.data.domain.Pageable

interface CustomRepository {

    fun <T : Any> executeNativeQuery(
        query: String,
        parameters: Map<String, Any>,
        page: Pageable? = null,
        mappedClass: Class<T>,
    ): List<T>
}
