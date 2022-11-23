package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.constant.BusinessConstants
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.COUNT
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.CURRENCY
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.TOTAL
import com.speedyteller.reporting.api.domain.model.request.GetReportRequest
import com.speedyteller.reporting.api.domain.model.response.GetReportResponse
import com.speedyteller.reporting.api.repository.TransactionRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.math.BigInteger
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.streams.toList

@Component
class GetReport(private val transaction: Transaction) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handle(request: GetReportRequest): List<GetReportResponse> {
        return try {
            transaction.get(request)
        } catch (ex: DataIntegrityViolationException) {
            // Tries again when having a unique constraint error due to data concurrency
            logger.warn(ex.message)
            transaction.get(request)
        }
    }

    @Transactional
    @Component
    class Transaction(val transactionRepository: TransactionRepository) {

        private val query: StringBuilder = StringBuilder().append(BusinessConstants.Queries.QUERY_GET_REPORT)

        fun get(request: GetReportRequest): List<GetReportResponse> {
            val params = fillParameters(request)
            query.append(" GROUP BY ft.original_currency")
            val resultList = transactionRepository.executeNativeQuery(query = query.toString(), parameters = params)
            return resultList.stream().map { record -> getReportRecord(record = record) }.toList<GetReportResponse>()
        }

        private fun fillParameters(request: GetReportRequest): Map<String, Any> {
            val parameters = mutableMapOf<String, Any>()
            request.fromDate?.let {
                query.append("AND tr.created_at >= :created_at_start ")
                parameters.plusAssign(Pair("created_at_start", LocalDateTime.of(it, LocalTime.MIDNIGHT)))
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
            return parameters
        }

        private fun getReportRecord(record: Array<Any>): GetReportResponse {
            return GetReportResponse(
                count = (record[COUNT] as? BigInteger)?.toLong(),
                total = record[TOTAL] as? BigDecimal,
                currency = record[CURRENCY] as? String
            )
        }
    }
}
