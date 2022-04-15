package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.AgentInfo
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.GetTransaction
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.extension.unwrap
import com.speedyteller.reporting.api.repository.AcquirerRepository
import com.speedyteller.reporting.api.repository.AgentInfoRepository
import com.speedyteller.reporting.api.repository.CustomerRepository
import com.speedyteller.reporting.api.repository.FXTransactionRepository
import com.speedyteller.reporting.api.repository.MerchantRepository
import com.speedyteller.reporting.api.repository.TransactionRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import com.speedyteller.reporting.api.domain.model.Transaction as TransactionModel

@Component
class FindTransactionById(private val transaction: Transaction) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handle(transactionId: String): GetTransaction {
        return try {
            transaction.find(transactionId)
        } catch (ex: DataIntegrityViolationException) {
            // Tries again when having a unique constraint error due to data concurrency
            logger.warn(ex.message)
            transaction.find(transactionId)
        }
    }

    @Transactional
    @Component
    class Transaction(
        val transactionRepository: TransactionRepository,
        val fxTransactionRepository: FXTransactionRepository,
        val customerRepository: CustomerRepository,
        val acquirerRepository: AcquirerRepository,
        val merchantRepository: MerchantRepository,
        val agentInfoRepository: AgentInfoRepository
    ) {

        fun find(id: String): GetTransaction {
            val transactionEntity = transactionRepository.findByTransactionId(transactionId = id)
                ?: throw NotFoundException("Transaction not found")
            val transactionModel = TransactionModel(entity = transactionEntity)
            val fxTransaction =
                transactionModel.fxTransactionId?.let { findFXTransaction(id = it) } ?: FXTransaction()
            val customer = transactionModel.customerId?.let { findCustomer(id = it) } ?: Customer()
            val acquirer = transactionModel.acquirerTransactionId?.let { findAcquirer(id = it) } ?: Acquirer()
            val merchant = transactionModel.merchantId?.let { findMerchant(id = it) } ?: Merchant()
            transactionModel.agent = transactionModel.agentInfoId?.let { findAgentInfo(id = it) }
            return GetTransaction(
                transaction = transactionModel,
                fx = fxTransaction,
                customerInfo = customer,
                acquirer = acquirer,
                merchant = merchant
            )
        }

        private fun findFXTransaction(id: Long): FXTransaction {
            val fxTransactionEntity = fxTransactionRepository.findById(id).unwrap()
                ?: throw NotFoundException("FXTransaction not found")
            return FXTransaction(entity = fxTransactionEntity)
        }

        private fun findCustomer(id: Long): Customer {
            val customerEntity = customerRepository.findById(id).unwrap()
                ?: throw NotFoundException("Customer not found")
            return Customer(entity = customerEntity)
        }

        private fun findAcquirer(id: Long): Acquirer {
            val acquirerEntity = acquirerRepository.findById(id).unwrap()
                ?: throw NotFoundException("Acquirer not found")
            return Acquirer(entity = acquirerEntity)
        }

        private fun findMerchant(id: Long): Merchant {
            val merchantEntity = merchantRepository.findById(id).unwrap()
                ?: throw NotFoundException("Merchant not found")
            return Merchant(entity = merchantEntity)
        }

        private fun findAgentInfo(id: Long): AgentInfo {
            val agentInfoEntity = agentInfoRepository.findById(id).unwrap()
                ?: throw NotFoundException("AgentInfo not found")
            return AgentInfo(entity = agentInfoEntity)
        }
    }
}
