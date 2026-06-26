package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.request.GetReportRequest
import com.speedyteller.reporting.api.domain.model.response.GetReportResponse

fun interface ReportService {

    fun getReport(request: GetReportRequest): List<GetReportResponse>
}
