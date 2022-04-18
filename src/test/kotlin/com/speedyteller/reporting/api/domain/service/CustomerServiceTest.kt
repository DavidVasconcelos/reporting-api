package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.response.GetCustomerResponse
import com.speedyteller.reporting.api.mock.MockTest
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class CustomerServiceTest {

    @Autowired
    private lateinit var service: CustomerService

    @Autowired
    private lateinit var mockTest: MockTest

    @Test
    fun `Get Customer`() {
        val customer = mockTest.getCustumer()
        val expectedCustomer = GetCustomerResponse(customerInfo = customer)
        val savedCustomer = service.getCustomer(transactionId = "1-1444392550-1")

        expectedCustomer shouldBeEqualTo savedCustomer
    }
}
