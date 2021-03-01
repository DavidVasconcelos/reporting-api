package com.speedyteller.reporting.api.repository

import org.springframework.data.domain.Pageable

interface CustomTransactionRepository {

    fun getTransactionList(query: String, parameters: Map<String, Any>, page: Pageable): List<Array<Any>>
}
