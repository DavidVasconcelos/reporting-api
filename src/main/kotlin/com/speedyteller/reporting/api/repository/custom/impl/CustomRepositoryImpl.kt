package com.speedyteller.reporting.api.repository.custom.impl

import com.speedyteller.reporting.api.repository.custom.CustomRepository
import org.springframework.data.domain.Pageable
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository

@Repository
class CustomRepositoryImpl(private val jdbcClient: JdbcClient) : CustomRepository {

    override fun <T : Any> executeNativeQuery(
        query: String,
        parameters: Map<String, Any>,
        page: Pageable?,
        mappedClass: Class<T>,
    ): List<T> {
        var finalQuery = query
        val finalParams = parameters.toMutableMap()

        page?.let {
            finalQuery += " LIMIT :limit OFFSET :offset"
            finalParams["limit"] = it.pageSize
            finalParams["offset"] = it.offset.toInt()
        }

        return jdbcClient.sql(finalQuery)
            .params(finalParams)
            .query(DataClassRowMapper(mappedClass))
            .list()
    }
}
