package com.speedyteller.reporting.api.domain.service.impl

import com.speedyteller.reporting.api.domain.model.Report
import com.speedyteller.reporting.api.domain.model.request.ReportRequest
import com.speedyteller.reporting.api.domain.service.ReportService
import com.speedyteller.reporting.api.domain.usecase.GetReport
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl(val getReport: GetReport) : ReportService {

    override fun getReport(request: ReportRequest): List<Report> {
        logger.info("Getting report with request $request")
        return getReport.handle(request = request)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
