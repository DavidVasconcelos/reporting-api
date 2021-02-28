package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.AgentInfo
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXMerchant
import com.speedyteller.reporting.api.domain.model.FXResponse
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.GetTransactionAcquirerResponse
import com.speedyteller.reporting.api.domain.model.GetTransactionMerchantResponse
import com.speedyteller.reporting.api.domain.model.GetTransactionMerchantTransactionResponse
import com.speedyteller.reporting.api.domain.model.GetTransactionResponse
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.service.TransactionService
import com.speedyteller.reporting.api.port.PostgresPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl : TransactionService {

    @Autowired
    private lateinit var postgresPort: PostgresPort

    override fun getTransaction(transactionId: String): GetTransactionResponse {

        val transaction = postgresPort.findTransactionByTransactionId(transactionId = transactionId)

        val fxTransaction = transaction.fxTransactionId?.let { fxTransactionId ->

            postgresPort.findFXTransactionById(id = fxTransactionId)

        } ?: FXTransaction()

        val customer = transaction.customerId?.let { customerId ->

            postgresPort.findCustomerById(id = customerId)

        } ?: Customer()

        val acquirer = transaction.acquirerTransactionId?.let { acquirerTransactionId ->

            postgresPort.findAcquirerById(id = acquirerTransactionId)

        } ?: Acquirer()

        val merchant = transaction.merchantId?.let { merchantId ->

            postgresPort.findMerchantById(id = merchantId)

        } ?: Merchant()

        transaction.agent = transaction.agentInfoId?.let { agentInfoId ->

            postgresPort.findAgentInfoById(id = agentInfoId)

        }

        return GetTransactionResponse(
            fx = FXResponse(merchant = FXMerchant(fxTransaction = fxTransaction)),
            customerInfo = customer,
            acquirer = GetTransactionAcquirerResponse(acquirer = acquirer),
            merchant = GetTransactionMerchantResponse(merchant = merchant),
            transaction = GetTransactionMerchantTransactionResponse(merchant = transaction)
        )
    }
}
