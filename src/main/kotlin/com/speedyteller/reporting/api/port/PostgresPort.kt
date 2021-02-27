package com.speedyteller.reporting.api.port

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.AgentInfo
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.InstantPaymentNotification
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.model.Transaction

interface PostgresPort {

    fun getAcquirer(id: Long): Acquirer

    fun getAgentInfo(id: Long): AgentInfo

    fun getCustomer(id: Long): Customer

    fun getFXTransaction(id: Long): FXTransaction

    fun getInstantPaymentNotification(id: Long): InstantPaymentNotification

    fun getMerchant(id: Long): Merchant

    fun getTransaction(id: Long): Transaction

}