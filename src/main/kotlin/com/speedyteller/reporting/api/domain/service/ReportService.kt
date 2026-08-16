package com.speedyteller.reporting.api.domain.service

import com.speedyteller.reporting.api.domain.model.Report
import com.speedyteller.reporting.api.domain.model.request.ReportRequest

fun interface ReportService {

    fun getReport(request: ReportRequest): List<Report>
}
