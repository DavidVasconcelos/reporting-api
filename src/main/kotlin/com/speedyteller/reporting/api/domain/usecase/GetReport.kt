package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.common.BusinessConstants
import com.speedyteller.reporting.api.domain.model.request.GetReportRequest
import com.speedyteller.reporting.api.domain.model.response.GetReportResponse
import com.speedyteller.reporting.api.repository.jpa.TransactionRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.LocalTime

@Component
class GetReport(private val transactionRepository: TransactionRepository) {

    @Transactional(readOnly = true)
    fun handle(request: GetReportRequest): List<GetReportResponse> {
        val (query, params) = buildQueryWithParams(request)
        return transactionRepository.executeNativeQuery(
            query = query,
            parameters = params,
            mappedClass = GetReportResponse::class.java,
        )
    }

    private fun buildQueryWithParams(request: GetReportRequest): Pair<String, Map<String, Any>> {
        val query: StringBuilder =
            StringBuilder().append(BusinessConstants.Queries.QUERY_GET_REPORT)
        val parameters = mutableMapOf<String, Any>()
        request.fromDate?.let {
            query.append("AND tr.created_at >= :created_at_start ")
            parameters.plusAssign(
                Pair(
                    "created_at_start",
                    LocalDateTime.of(it, LocalTime.MIDNIGHT),
                ),
            )
        }
        request.toDate?.let {
            query.append("AND tr.created_at <= :created_at_end ")
            parameters.plusAssign(Pair("created_at_end", LocalDateTime.of(it, LocalTime.MAX)))
        }
        request.merchant?.let {
            query.append("AND tr.merchant_id = :merchant ")
            parameters.plusAssign(Pair("merchant", it))
        }
        request.acquirer?.let {
            query.append("AND tr.acquirer_transaction_id = :acquirer ")
            parameters.plusAssign(Pair("acquirer", it))
        }
        query.append(" GROUP BY ft.original_currency")
        return Pair(query.toString(), parameters)
    }
}
