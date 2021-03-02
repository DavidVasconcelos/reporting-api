package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.ReportingApiApplicationTests
import com.speedyteller.reporting.api.config.PostgresContainerSetup
import com.speedyteller.reporting.api.domain.model.response.GetTransactionResponse
import com.speedyteller.reporting.api.mock.MockTest
import com.speedyteller.reporting.api.port.PostgresPort
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(classes = [ReportingApiApplicationTests::class])
@ContextConfiguration(initializers = [PostgresContainerSetup::class])
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionServiceTest {

    @Autowired
    private lateinit var service: TransactionService

    @Autowired
    private lateinit var postgresPort: PostgresPort

    @Autowired
    private lateinit var mockTest: MockTest

    @Test
    fun `Get Transaction`() {

        val transaction = mockTest.getTransactionResponse()

        val savedTransaction = service.getTransaction(transactionId = "1-1444392550-1")

        Assertions.assertEquals(transaction, savedTransaction)

    }
}