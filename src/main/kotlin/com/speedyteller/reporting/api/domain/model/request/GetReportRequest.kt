package com.speedyteller.reporting.api.domain.model.request

import com.speedyteller.reporting.api.domain.constant.BusinessConstants.RegexFormats.REGEX_DATE_FORMAT_VALIDATOR
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.ValidatorMessages.DATE_FORMAT_VALIDATOR_MESSAGE
import com.speedyteller.reporting.api.domain.dto.request.GetReportRequestDTO
import com.speedyteller.reporting.api.exception.BusinessValidationException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class GetReportRequest(
    var fromDate: LocalDate? = null,
    var toDate: LocalDate? = null,
    var merchant: Int? = null,
    var acquirer: Int? = null,
) {
    constructor(dto: GetReportRequestDTO) : this() {
        this.validateFields(dto)
        this.fromDate = dto.fromDate?.let { LocalDate.parse(it, DateTimeFormatter.ISO_DATE) }
        this.toDate = dto.toDate?.let { LocalDate.parse(it, DateTimeFormatter.ISO_DATE) }
        this.merchant = dto.merchant
        this.acquirer = dto.acquirer
    }

    private fun validateFields(dto: GetReportRequestDTO) {
        val dateRegex = Regex(REGEX_DATE_FORMAT_VALIDATOR)

        fun requireDateMatches(value: String?) {
            if (value != null && !value.matches(dateRegex)) {
                throw BusinessValidationException(DATE_FORMAT_VALIDATOR_MESSAGE)
            }
        }

        requireDateMatches(dto.fromDate)
        requireDateMatches(dto.toDate)
    }
}
