package com.speedyteller.reporting.api.port

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.AgentInfo
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.InstantPaymentNotification
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.model.Transaction

interface PostgresPort {

    fun findAcquirerById(id: Long): Acquirer

    fun findAgentInfoById(id: Long): AgentInfo

    fun findCustomerById(id: Long): Customer

    fun findFXTransactionById(id: Long): FXTransaction

    fun findInstantPaymentNotificationById(id: Long): InstantPaymentNotification

    fun findMerchantById(id: Long): Merchant

    fun findTransactionById(transactionId: String): Transaction

}