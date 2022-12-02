package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.request.GetReportRequest
import com.speedyteller.reporting.api.mock.MockTest
import com.speedyteller.reporting.api.support.annotations.IntegrationTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

@IntegrationTest
class ReportServiceTest {

    @Autowired
    private lateinit var reportService: ReportService

    @Autowired
    private lateinit var mockTest: MockTest

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
