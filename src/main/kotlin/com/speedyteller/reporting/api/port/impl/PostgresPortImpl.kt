package com.speedyteller.reporting.api.port.impl

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.AgentInfo
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.InstantPaymentNotification
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.model.Transaction
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
import org.springframework.stereotype.Component

@Component
class PostgresPortImpl @Autowired constructor(
    val acquirerRepository: AcquirerRepository,
    val agentInfoRepository: AgentInfoRepository,
    val customerRepository: CustomerRepository,
    val fxTransactionRepository: FXTransactionRepository,
    val instantPaymentNotificationRepository: InstantPaymentNotificationRepository,
    val merchantRepository: MerchantRepository,
    val transactionRepository: TransactionRepository
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

    override fun findTransactionById(transactionId: String): Transaction {

        val transaction = transactionRepository.findByTransactionId(transactionId = transactionId)
            ?: throw NotFoundException("Transaction $NOT_FOUND_ERROR_MESSAGE")

        return Transaction()
    }

    companion object {
        const val NOT_FOUND_ERROR_MESSAGE = "not found"
    }
}
