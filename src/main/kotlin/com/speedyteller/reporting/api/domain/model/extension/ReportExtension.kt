package com.speedyteller.reporting.api.domain.model.extension

import com.speedyteller.reporting.api.domain.model.Report
import com.speedyteller.reporting.api.web.dto.response.ReportResponseDTO.ReportInfo

fun Report.toDTO(): ReportInfo = ReportInfo(
    count = this.count,
    total = this.total,
    currency = this.currency,
)
