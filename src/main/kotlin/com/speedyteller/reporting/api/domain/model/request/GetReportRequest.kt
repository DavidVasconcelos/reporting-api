package com.speedyteller.reporting.api.domain.model.request

import com.speedyteller.reporting.api.domain.constant.BusinessConstants.RegexFormats.REGEX_DATE_FORMAT_VALIDATOR
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.ValidatorMessages.DATE_FORMAT_VALIDATOR_MESSAGE
import com.speedyteller.reporting.api.domain.dto.request.GetReportRequestDTO
import com.speedyteller.reporting.api.exception.BusinessValidationException
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.matches
import org.valiktor.validate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class GetReportRequest(
    var fromDate: LocalDate? = null,
    var toDate: LocalDate? = null,
    var merchant: Int? = null,
    var acquirer: Int? = null
) {
    constructor(dto: GetReportRequestDTO) : this() {
        try {
            validate(dto) {
                validate(GetReportRequestDTO::fromDate)
                    .matches(Regex(REGEX_DATE_FORMAT_VALIDATOR))
                validate(GetReportRequestDTO::toDate)
                    .matches(Regex(REGEX_DATE_FORMAT_VALIDATOR))
            }
            this.fromDate = dto.fromDate?.let { LocalDate.parse(it, DateTimeFormatter.ISO_DATE) }
            this.toDate = dto.toDate?.let { LocalDate.parse(it, DateTimeFormatter.ISO_DATE) }
            this.merchant = dto.merchant
            this.acquirer = dto.acquirer
        } catch (ex: ConstraintViolationException) {
            this.interpretConstraintViolation(exception = ex)
        }
    }

    private fun interpretConstraintViolation(exception: ConstraintViolationException) {
        val error = exception.constraintViolations.toList().first()
        when {
            ((error.property == "fromDate" || error.property == "toDate") &&
                    (error.constraint.name == "Matches")) -> {
                throw BusinessValidationException(DATE_FORMAT_VALIDATOR_MESSAGE)
            }
            else -> throw BusinessValidationException("${error.property}: ${error.constraint.name}")
        }
    }
}
