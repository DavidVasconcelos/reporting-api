package com.speedyteller.reporting.api.extension

import com.speedyteller.reporting.api.domain.constant.BusinessConstants.Patterns.DATE_TIME_PATTERN
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.toStringPattern(): String =
    this.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN))
