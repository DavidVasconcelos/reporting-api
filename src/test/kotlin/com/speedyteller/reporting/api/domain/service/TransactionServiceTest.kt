package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.enum.FilterField
import com.speedyteller.reporting.api.domain.model.enum.Operation
import com.speedyteller.reporting.api.domain.model.enum.PaymentMethod
import com.speedyteller.reporting.api.domain.model.enum.Status
import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.mock.MockTest
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import com.speedyteller.reporting.api.web.controller.TransactionController
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import java.time.LocalDate

@IntegrationTest
class TransactionServiceTest {

    @Autowired
    private lateinit var service: TransactionService

    @Autowired
    private lateinit var mockTest: MockTest

    @Test
    fun `Get Transaction`() {
        val expectedTransaction = mockTest.getTransaction()
        val savedTransaction = service.getTransaction(transactionId = "1-1444392550-1")

        expectedTransaction shouldBeEqualTo savedTransaction
    }

    @Test
    fun `Get Transaction List`() {
        val expectedTransaction = mockTest.getTransactionListResponse()
        val savedTransaction = service.getTransactionList(
            request = GetTransactionListRequest(
                fromDate = LocalDate.of(2015, 9, 29),
                toDate = LocalDate.of(2015, 10, 29),
                status = Status.getStatus("APPROVED"),
                operation = Operation.getOperation("3DAUTH"),
                paymentMethod = PaymentMethod.getPaymentMethod("CREDITCARD"),
                errorCode = null,
                filterField = FilterField.getFilterField("Reference No"),
                filterValue = "api_560a4a9314208",
                merchantId = 3,
                acquirerId = 1,
            ),
            page = PageRequest.of(0, TransactionController.DEFAULT_PAGE_SIZE),
        )
        expectedTransaction shouldBeEqualTo savedTransaction
    }
}
