package com.speedyteller.reporting.api.domain.model.request

import com.speedyteller.reporting.api.domain.constant.BusinessConstants.RegexFormats.REGEX_DATE_FORMAT_VALIDATOR
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.ValidatorMessages.DATE_FORMAT_VALIDATOR_MESSAGE
import com.speedyteller.reporting.api.domain.dto.request.GetTransactionListRequestDTO
import com.speedyteller.reporting.api.domain.enum.ErrorCode
import com.speedyteller.reporting.api.domain.enum.FilterField
import com.speedyteller.reporting.api.domain.enum.Operation
import com.speedyteller.reporting.api.domain.enum.PaymentMethod
import com.speedyteller.reporting.api.domain.enum.Status
import com.speedyteller.reporting.api.exception.BusinessValidationException
import com.speedyteller.reporting.api.extension.toCapital
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.enums.enumEntries

data class GetTransactionListRequest(
    var fromDate: LocalDate? = null,
    var toDate: LocalDate? = null,
    var status: Status? = null,
    var operation: Operation? = null,
    var paymentMethod: PaymentMethod? = null,
    var errorCode: ErrorCode? = null,
    var filterField: FilterField? = null,
    var filterValue: String? = null,
    var merchantId: Int? = null,
    var acquirerId: Int? = null,
) {
    constructor(dto: GetTransactionListRequestDTO) : this() {
        this.validateFields(dto)
        this.fromDate = dto.fromDate?.let { LocalDate.parse(it, DateTimeFormatter.ISO_DATE) }
        this.toDate = dto.toDate?.let { LocalDate.parse(it, DateTimeFormatter.ISO_DATE) }
        this.status = dto.status?.let { Status.getStatus(status = it) }
        this.operation = dto.operation?.let { Operation.getOperation(operation = it) }
        this.paymentMethod =
            dto.paymentMethod?.let { PaymentMethod.getPaymentMethod(paymentMethod = it) }
        this.errorCode = dto.errorCode?.let { ErrorCode.getErrorCode(errorCode = it) }
        this.filterField = dto.filterField?.let { FilterField.getFilterField(filterField = it) }
        this.filterValue = dto.filterValue
        this.merchantId = dto.merchantId
        this.acquirerId = dto.acquirerId
    }

    private fun validateFields(dto: GetTransactionListRequestDTO) {
        requireDateMatches(dto.fromDate)
        requireDateMatches(dto.toDate)

        requireInEnum(dto.status, enumEntries<Status>().map { it.name }, "status")
        requireInEnum(dto.operation, enumEntries<Operation>().map { it.operation }, "operation")
        requireInEnum(
            dto.paymentMethod,
            enumEntries<PaymentMethod>().map { it.name },
            "paymentMethod",
        )
        requireInEnum(dto.errorCode, enumEntries<ErrorCode>().map { it.errorCode }, "errorCode")
        requireInEnum(
            dto.filterField,
            enumEntries<FilterField>().map { it.filterField },
            "filterField",
        )
    }

    fun requireDateMatches(value: String?) {
        val dateRegex = Regex(REGEX_DATE_FORMAT_VALIDATOR)

        if (value != null && !value.matches(dateRegex)) {
            throw BusinessValidationException(DATE_FORMAT_VALIDATOR_MESSAGE)
        }
    }

    fun requireInEnum(value: String?, validValues: List<String>, fieldName: String) {
        if (value != null && value !in validValues) {
            throw BusinessValidationException("Invalid ${fieldName.toCapital()}")
        }
    }
}
