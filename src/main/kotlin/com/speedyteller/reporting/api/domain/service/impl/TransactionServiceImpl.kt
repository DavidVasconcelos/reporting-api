package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.domain.model.response.FXMerchant
import com.speedyteller.reporting.api.domain.model.response.FXResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionAcquirerResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionMerchantResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionMerchantTransactionResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionResponse
import com.speedyteller.reporting.api.domain.service.TransactionService
import com.speedyteller.reporting.api.port.PostgresPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl : TransactionService {

    @Autowired
    private lateinit var postgresPort: PostgresPort

    override fun getTransaction(transactionId: String): GetTransactionResponse {

        val transaction = postgresPort.findTransactionByTransactionId(transactionId = transactionId)

        val fxTransaction =
            transaction.fxTransactionId?.let { getFxTransaction(fxTransactionId = it) } ?: FXTransaction()
        val customer = transaction.customerId?.let { getCustomer(customerId = it) } ?: Customer()
        val acquirer = transaction.acquirerTransactionId?.let { getAcquirer(acquirerTransactionId = it) } ?: Acquirer()
        val merchant = transaction.merchantId?.let { getMerchant(merchantId = it) } ?: Merchant()

        transaction.agent = transaction.agentInfoId?.let { getAgent(agentInfoId = it) }

        return GetTransactionResponse(
            fx = FXResponse(merchant = FXMerchant(fxTransaction = fxTransaction)),
            customerInfo = customer,
            acquirer = GetTransactionAcquirerResponse(acquirer = acquirer),
            merchant = GetTransactionMerchantResponse(merchant = merchant),
            transaction = GetTransactionMerchantTransactionResponse(merchant = transaction)
        )
    }

    override fun getTransactionList(request: GetTransactionListRequest): Page<GetTransactionListResponse> {
        TODO("Not yet implemented")
    }

    private fun getFxTransaction(fxTransactionId: Long) =
        postgresPort.findFXTransactionById(id = fxTransactionId)

    private fun getCustomer(customerId: Long) =
        postgresPort.findCustomerById(id = customerId)

    private fun getAcquirer(acquirerTransactionId: Long) =
        postgresPort.findAcquirerById(id = acquirerTransactionId)

    private fun getMerchant(merchantId: Long) =
        postgresPort.findMerchantById(id = merchantId)

    private fun getAgent(agentInfoId: Long) =
        postgresPort.findAgentInfoById(id = agentInfoId)

}
