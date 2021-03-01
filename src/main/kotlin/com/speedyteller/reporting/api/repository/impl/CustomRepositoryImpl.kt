package com.speedyteller.reporting.api.repository.impl

import com.speedyteller.reporting.api.repository.CustomRepository
import org.springframework.data.domain.Pageable
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class CustomRepositoryImpl : CustomRepository {

    @PersistenceContext
    private lateinit var em: EntityManager

    override fun executeNativeQuery(
        query: String,
        parameters: Map<String, Any>,
        page: Pageable
    ): List<Array<Any>> {

        val nativeQuery = em.createNativeQuery(query)

        parameters.forEach { (key, value) -> nativeQuery.setParameter(key, value) }

        page.let {
            nativeQuery.firstResult = (it.pageNumber.minus(ONE)).times(it.pageSize)
            nativeQuery.maxResults = it.pageSize
        }

        return nativeQuery.resultList as List<Array<Any>>
    }

    companion object {
        const val ONE = 1
    }
}
