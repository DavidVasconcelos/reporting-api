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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PostgresPortImpl @Autowired constructor(val acquirerRepository: AcquirerRepository) : PostgresPort {

    override fun getAcquirer(id: Long): Acquirer {

        val acquirerEntity =
            acquirerRepository.findById(id).unwrap() ?: throw NotFoundException("Acquirer $NOT_FOUND_ERROR_MESSAGE")

        return Acquirer(entity = acquirerEntity)
    }

    override fun getAgentInfo(id: Long): AgentInfo {
        TODO("Not yet implemented")
    }

    override fun getCustomer(id: Long): Customer {
        TODO("Not yet implemented")
    }

    override fun getFXTransaction(id: Long): FXTransaction {
        TODO("Not yet implemented")
    }

    override fun getInstantPaymentNotification(id: Long): InstantPaymentNotification {
        TODO("Not yet implemented")
    }

    override fun getMerchant(id: Long): Merchant {
        TODO("Not yet implemented")
    }

    override fun getTransaction(id: Long): Transaction {
        TODO("Not yet implemented")
    }

    companion object {
        const val NOT_FOUND_ERROR_MESSAGE = "not found"
    }
}