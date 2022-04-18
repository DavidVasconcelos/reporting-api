package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.controller.TransactionController
import com.speedyteller.reporting.api.domain.model.request.GetReportRequest
import com.speedyteller.reporting.api.domain.model.request.GetTransactionListRequest
import com.speedyteller.reporting.api.mock.MockTest
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
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
    private lateinit var reportService: ReportService

    @Autowired
    private lateinit var mockTest: MockTest

    @Test
    fun `Get Transaction`() {
        val expectedTransaction = mockTest.getTransactionResponse()
        val savedTransaction = service.getTransaction(transactionId = "1-1444392550-1")

        expectedTransaction shouldBeEqualTo savedTransaction
    }

    @Test
    fun `Get Transaction List`() {
        val expectedTransaction = mockTest.getTransactionListResponse()
        val savedTransaction = service.getTransactionList(
            request = GetTransactionListRequest(
                merchantId = 3,
                acquirerId = 12
            ), page = PageRequest.of(1, TransactionController.DEFAULT_PAGE_SIZE)
        )

        expectedTransaction shouldBeEqualTo savedTransaction
    }

    @Test
    fun `Get Report`() {
        val expectedReport = mockTest.getReportResponse()
        val savedReport = reportService.getReport(
            GetReportRequest(
                fromDate = LocalDate.of(2015, 9, 29),
                toDate = LocalDate.of(2015, 10, 9),
                merchant = 1,
                acquirer = 1
            )
        )

        expectedReport shouldBeEqualTo savedReport
    }
}
