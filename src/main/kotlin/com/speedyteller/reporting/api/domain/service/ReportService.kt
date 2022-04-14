package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.request.GetReportRequest
import com.speedyteller.reporting.api.domain.model.response.GetReportResponse
import com.speedyteller.reporting.api.domain.usecase.GetReport
import org.springframework.stereotype.Service

@Service
class ReportService(val getReport: GetReport) {

    fun getReport(request: GetReportRequest): List<GetReportResponse> =
        getReport.handle(request = request)
}
