package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.request.GetReportRequest
import com.speedyteller.reporting.api.domain.model.response.GetReportResponse
import com.speedyteller.reporting.api.domain.service.ReportService
import com.speedyteller.reporting.api.domain.usecase.GetReport
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl(val getReport: GetReport) : ReportService {

    override fun getReport(request: GetReportRequest): List<GetReportResponse> = getReport.handle(request = request)
}
