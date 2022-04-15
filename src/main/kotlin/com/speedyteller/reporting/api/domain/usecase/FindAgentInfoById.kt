package com.speedyteller.reporting.api.domain.usecase

import com.speedyteller.reporting.api.domain.model.AgentInfo
import com.speedyteller.reporting.api.exception.NotFoundException
import com.speedyteller.reporting.api.extension.unwrap
import com.speedyteller.reporting.api.repository.AgentInfoRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class FindAgentInfoById(private val transaction: Transaction) {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun handle(agentInfoId: Long): AgentInfo {
        return try {
            transaction.find(agentInfoId)
        } catch (ex: DataIntegrityViolationException) {
            // Tries again when having a unique constraint error due to data concurrency
            logger.warn(ex.message)
            transaction.find(agentInfoId)
        }
    }

    @Transactional
    @Component
    class Transaction(val agentInfoRepository: AgentInfoRepository) {

        fun find(id: Long): AgentInfo {
            val agentInfoEntity = agentInfoRepository.findById(id).unwrap()
                ?: throw NotFoundException("AgentInfo not found")
            return AgentInfo(entity = agentInfoEntity)
        }
    }
}
