package com.speedyteller.reporting.api.port.impl

import com.speedyteller.reporting.api.config.FilterFieldComponent
import com.speedyteller.reporting.api.domain.constant.BusinessConstants
import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.AgentInfo
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.GetTransactionList
import com.speedyteller.reporting.api.domain.model.InstantPaymentNotification
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.model.Transaction
import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.extension.unwrap
import com.speedyteller.reporting.api.port.PostgresPort
import com.speedyteller.reporting.api.repository.AcquirerRepository
import com.speedyteller.reporting.api.repository.AgentInfoRepository
import com.speedyteller.reporting.api.repository.CustomerRepository
import com.speedyteller.reporting.api.repository.FXTransactionRepository
import com.speedyteller.reporting.api.repository.InstantPaymentNotificationRepository
import com.speedyteller.reporting.api.repository.MerchantRepository
import com.speedyteller.reporting.api.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.BigInteger
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.LocalTime

@Component
class PostgresPortImpl @Autowired constructor(
    val acquirerRepository: AcquirerRepository,
    val agentInfoRepository: AgentInfoRepository,
    val customerRepository: CustomerRepository,
    val fxTransactionRepository: FXTransactionRepository,
    val instantPaymentNotificationRepository: InstantPaymentNotificationRepository,
    val merchantRepository: MerchantRepository,
    val transactionRepository: TransactionRepository,
    val filterFieldComponent: FilterFieldComponent
) : PostgresPort {

    override fun findAcquirerById(id: Long): Acquirer {

        val acquirerEntity =
            acquirerRepository.findById(id).unwrap() ?: throw NotFoundException("Acquirer $NOT_FOUND_ERROR_MESSAGE")

        return Acquirer(entity = acquirerEntity)
    }

    override fun findAgentInfoById(id: Long): AgentInfo {

        val agentInfoEntity =
            agentInfoRepository.findById(id).unwrap() ?: throw NotFoundException("AgentInfo $NOT_FOUND_ERROR_MESSAGE")

        return AgentInfo(entity = agentInfoEntity)
    }

    override fun findCustomerById(id: Long): Customer {

        val customerEntity =
            customerRepository.findById(id).unwrap() ?: throw NotFoundException("Customer $NOT_FOUND_ERROR_MESSAGE")

        return Customer(entity = customerEntity)
    }

    override fun findFXTransactionById(id: Long): FXTransaction {

        val fxTransactionEntity = fxTransactionRepository.findById(id).unwrap()
            ?: throw NotFoundException("FXTransaction $NOT_FOUND_ERROR_MESSAGE")

        return FXTransaction(entity = fxTransactionEntity)
    }

    override fun findInstantPaymentNotificationById(id: Long): InstantPaymentNotification {

        val instantPaymentNotificationEntity = instantPaymentNotificationRepository.findById(id).unwrap()
            ?: throw NotFoundException("IPN $NOT_FOUND_ERROR_MESSAGE")

        return InstantPaymentNotification(enity = instantPaymentNotificationEntity)
    }

    override fun findMerchantById(id: Long): Merchant {

        val merchantEntity =
            merchantRepository.findById(id).unwrap() ?: throw NotFoundException("Merchant $NOT_FOUND_ERROR_MESSAGE")

        return Merchant(entity = merchantEntity)
    }

    override fun findTransactionByTransactionId(transactionId: String): Transaction {

        val transactionEntity = transactionRepository.findByTransactionId(transactionId = transactionId)
            ?: throw NotFoundException("Transaction $NOT_FOUND_ERROR_MESSAGE")

        return Transaction(entity = transactionEntity)
    }

    override fun findTransactionList(request: GetTransactionListRequest, page: Pageable): List<GetTransactionList> {

        val parameters = mutableMapOf<String, Any>()

        val query = StringBuilder().append(BusinessConstants.QUER_GET_TRANSACTION_LIST)

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

        val resultList =
            transactionRepository.getTransactionList(query = query.toString(), page = page, parameters = parameters)

        val transactionList = mutableListOf<GetTransactionList>()

        resultList.stream().forEach { record -> transactionList.add(getTransactionRecord(record = record)) }

        return transactionList
    }

    private fun getTransactionRecord(record: Array<Any>): GetTransactionList {

        return GetTransactionList(
            originalAmount = record[ORIGINAL_AMOUNT] as? BigDecimal,
            originalCurrency = record[ORIGINAL_CURRENCY] as? String,
            number = record[NUMBER] as? String,
            email = record[EMAIL] as? String,
            billingFirstName = record[BILLING_FIRST_NAME] as? String,
            billingLastName = record[BILLING_LAST_NAME] as? String,
            merchantId = (record[MERCHANT_ID] as? BigInteger)?.toLong(),
            merchantName = record[MERCHANT_NAME] as? String,
            received = record[RECEIVED] as? Boolean,
            referenceNo = record[REFERENCE_NO] as? String,
            status = record[STATUS] as? String,
            operation = record[OPERATION] as? String,
            message = record[MESSAGE] as? String,
            created_at = (record[CREATED_AT] as? Timestamp)?.toLocalDateTime(),
            transactionId = record[TRANSACTION_ID] as? String,
            acquirerId = (record[ACQUIRER_ID] as? BigInteger)?.toLong(),
            acquirerName = record[ACQUIRER_NAME] as? String,
            acquirerCode = record[ACQUIRER_CODE] as? String,
            acquirerType = record[ACQUIRER_TYPE] as? String,
            refundable = record[REFUNDABLE] as? Boolean ?: false
        )

    }

    companion object {
        const val NOT_FOUND_ERROR_MESSAGE = "not found"
        const val ORIGINAL_AMOUNT = 0
        const val ORIGINAL_CURRENCY = 1
        const val NUMBER = 2
        const val EMAIL = 3
        const val BILLING_FIRST_NAME = 4
        const val BILLING_LAST_NAME = 5
        const val MERCHANT_ID = 6
        const val MERCHANT_NAME = 7
        const val RECEIVED = 8
        const val REFERENCE_NO = 9
        const val STATUS = 10
        const val OPERATION = 11
        const val MESSAGE = 12
        const val CREATED_AT = 13
        const val TRANSACTION_ID = 14
        const val ACQUIRER_ID = 15
        const val ACQUIRER_NAME = 16
        const val ACQUIRER_CODE = 17
        const val ACQUIRER_TYPE = 18
        const val REFUNDABLE = 19
    }
}
