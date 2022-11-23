package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.common.FilterFieldComponent
import com.speedyteller.reporting.api.domain.constant.BusinessConstants
import com.speedyteller.reporting.api.domain.model.GetTransactionList
import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.repository.TransactionRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.streams.toList

@Component
class GetTransactions(private val transaction: Transaction) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handle(
        request: GetTransactionListRequest,
        page: Pageable
    ): List<GetTransactionList> {
        return try {
            transaction.get(request, page)
        } catch (ex: DataIntegrityViolationException) {
            // Tries again when having a unique constraint error due to data concurrency
            logger.warn(ex.message)
            transaction.get(request, page)
        }
    }

    @Transactional
    @Component
    class Transaction(
        val transactionRepository: TransactionRepository,
        val filterFieldComponent: FilterFieldComponent,
    ) {

        fun get(request: GetTransactionListRequest, page: Pageable): List<GetTransactionList> {
            val (query, params) = buildQueryWithParams(request)
            val resultList = transactionRepository.executeNativeQuery(query = query, page = page, parameters = params)
            return resultList.stream().map { record -> GetTransactionList(record) }.toList<GetTransactionList>()
        }

        private fun buildQueryWithParams(request: GetTransactionListRequest): Pair<String, Map<String, Any>> {
            val query: StringBuilder = StringBuilder().append(BusinessConstants.Queries.QUERY_GET_TRANSACTION_LIST)
            val parameters = mutableMapOf<String, Any>()
            request.fromDate?.let {
                query.append("AND tr.created_at >= :created_at_start ")
                parameters.plusAssign(Pair("created_at_start", LocalDateTime.of(it, LocalTime.MIDNIGHT)))
            }
            request.toDate?.let {
                query.append("AND tr.created_at <= :created_at_end ")
                parameters.plusAssign(Pair("created_at_end", LocalDateTime.of(it, LocalTime.MAX)))
            }
            request.status?.let {
                query.append("AND tr.status = :status ")
                parameters.plusAssign(Pair("status", it.name))
            }
            request.operation?.let {
                query.append("AND tr.operation = :operation ")
                parameters.plusAssign(Pair("operation", it.operation))
            }
            request.paymentMethod?.let {
                query.append("AND a.type = :paymentMethod ")
                parameters.plusAssign(Pair("paymentMethod", it.name))
            }
            request.errorCode?.let {
                query.append("AND tr.error_code = :errorCode ")
                parameters.plusAssign(Pair("errorCode", it.errorCode))
            }
            request.filterValue?.let { filterValue ->
                request.filterField?.let { filterField ->
                    val field = filterFieldComponent.interpret(filterField)
                    query.append("AND $field = :customField ")
                    parameters.plusAssign(Pair("customField", filterValue))
                }
            }
            request.merchantId?.let {
                query.append("AND tr.merchant_id = :merchantId ")
                parameters.plusAssign(Pair("merchantId", it))
            }
            request.acquirerId?.let {
                query.append("AND tr.acquirer_transaction_id = :acquirerId ")
                parameters.plusAssign(Pair("acquirerId", it))
            }
            return Pair(query.toString(), parameters)
        }
    }
}
