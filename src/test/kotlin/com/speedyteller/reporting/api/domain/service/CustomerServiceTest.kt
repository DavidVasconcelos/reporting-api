package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.ReportingApiApplicationTests
import com.speedyteller.reporting.api.config.PostgresContainerSetup
import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.mock.MockTest
import com.speedyteller.reporting.api.port.PostgresPort
import org.junit.jupiter.api.Assertions.assertEquals
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
class CustomerServiceTest {

    @Autowired
    private lateinit var service: CustomerService

    @Autowired
    private lateinit var mockTest: MockTest

    @Test
    fun `Get Customer`() {

        val customer = mockTest.getCustumer()

        val getCustomerResponse = GetCustomerResponse(customerInfo = customer)

        val savedCustomer = service.getCustomer(transactionId = "1-1444392550-1")

        assertEquals(getCustomerResponse, savedCustomer)
    }

}
