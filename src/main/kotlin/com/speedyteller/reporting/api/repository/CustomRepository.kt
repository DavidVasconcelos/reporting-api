package com.speedyteller.reporting.api.repository

import org.springframework.data.domain.Pageable

interface CustomRepository {

    fun executeNativeQuery(query: String, parameters: Map<String, Any>, page: Pageable? = null): List<Array<Any>>
}
