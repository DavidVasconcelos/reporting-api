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
import org.valiktor.ConstraintViolation
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.isIn
import org.valiktor.functions.matches
import org.valiktor.validate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
    var acquirerId: Int? = null
) {
    constructor(dto: GetTransactionListRequestDTO) : this() {
        this.validateFields(dto)
        this.fromDate = dto.fromDate?.let { LocalDate.parse(it, DateTimeFormatter.ISO_DATE) }
        this.toDate = dto.toDate?.let { LocalDate.parse(it, DateTimeFormatter.ISO_DATE) }
        this.status = dto.status?.let { Status.getStatus(status = it) }
        this.operation = dto.operation?.let { Operation.getOperation(operation = it) }
        this.paymentMethod = dto.paymentMethod?.let { PaymentMethod.getPaymentMethod(paymentMethod = it) }
        this.errorCode = dto.errorCode?.let { ErrorCode.getErrorCode(errorCode = it) }
        this.filterField = dto.filterField?.let { FilterField.getFilterField(filterField = it) }
        this.filterValue = dto.filterValue
        this.merchantId = dto.merchantId
        this.acquirerId = dto.acquirerId
    }

    private fun validateFields(dto: GetTransactionListRequestDTO) {
        try {
            validate(dto) {
                validate(GetTransactionListRequestDTO::fromDate)
                    .matches(Regex(REGEX_DATE_FORMAT_VALIDATOR))
                validate(GetTransactionListRequestDTO::toDate)
                    .matches(Regex(REGEX_DATE_FORMAT_VALIDATOR))
                validate(GetTransactionListRequestDTO::status)
                    .isIn(enumValues<Status>().toList().map { it.name })
                validate(GetTransactionListRequestDTO::operation)
                    .isIn(enumValues<Operation>().toList().map { it.operation })
                validate(GetTransactionListRequestDTO::paymentMethod)
                    .isIn(enumValues<PaymentMethod>().toList().map { it.name })
                validate(GetTransactionListRequestDTO::errorCode)
                    .isIn(enumValues<ErrorCode>().toList().map { it.errorCode })
                validate(GetTransactionListRequestDTO::filterField)
                    .isIn(enumValues<FilterField>().toList().map { it.filterField })
            }
        } catch (ex: ConstraintViolationException) {
            this.handleConstraintViolation(error = ex.constraintViolations.toList().first())
        }
    }

    private fun handleConstraintViolation(error: ConstraintViolation) {
        val fieldsList = listOf("status", "operation", "paymentMethod", "errorCode", "filterField")
        val errorMessage = when {
            ((error.property == "fromDate" || error.property == "toDate") && (error.constraint.name == "Matches")) ->
                DATE_FORMAT_VALIDATOR_MESSAGE
            (fieldsList.contains(error.property) && error.constraint.name == "In") ->
                "Invalid ${error.property.toCapital()}"
            else -> "${error.property}: ${error.constraint.name}"
        }
        throw BusinessValidationException(errorMessage)
    }
}
