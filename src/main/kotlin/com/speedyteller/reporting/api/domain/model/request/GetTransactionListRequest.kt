package com.speedyteller.reporting.api.domain.model.request

import com.speedyteller.reporting.api.domain.constant.BusinessConstants
import com.speedyteller.reporting.api.domain.dto.request.GetTransactionListRequestDTO
import com.speedyteller.reporting.api.domain.enum.ErrorCode
import com.speedyteller.reporting.api.domain.enum.FilterField
import com.speedyteller.reporting.api.domain.enum.Operation
import com.speedyteller.reporting.api.domain.enum.PaymentMethod
import com.speedyteller.reporting.api.domain.enum.Status
import com.speedyteller.reporting.api.exception.BusinessValidationException
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

        try {

            validate(dto) {

                validate(GetTransactionListRequestDTO::fromDate)
                    .matches(Regex(BusinessConstants.REGEX_DATE_FORMAT_VALIDATOR))
                validate(GetTransactionListRequestDTO::toDate)
                    .matches(Regex(BusinessConstants.REGEX_DATE_FORMAT_VALIDATOR))
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

        } catch (ex: ConstraintViolationException) {

            this.interpretConstraintViolation(exception = ex)
        }
    }

    private fun interpretConstraintViolation(exception: ConstraintViolationException) {

        val error = exception.constraintViolations.toList().first()

        when {

            ((error.property == "fromDate" || error.property == "toDate") &&
                    (error.constraint.name == "Matches")) -> {
                throw BusinessValidationException(BusinessConstants.DATE_FORMAT_VALIDATOR_MESSAGE)
            }

            (error.property == "status" && error.constraint.name == "In") -> {
                throw BusinessValidationException(BusinessConstants.STATUS_VALIDATOR_MESSAGE)
            }

            (error.property == "operation" && error.constraint.name == "In") -> {
                throw BusinessValidationException(BusinessConstants.OPERATION_VALIDATOR_MESSAGE)
            }

            (error.property == "paymentMethod" && error.constraint.name == "In") -> {
                throw BusinessValidationException(BusinessConstants.PAYMENT_METHOD_VALIDATOR_MESSAGE)
            }

            (error.property == "errorCode" && error.constraint.name == "In") -> {
                throw BusinessValidationException(BusinessConstants.ERROR_CODE_VALIDATOR_MESSAGE)
            }

            (error.property == "filterField" && error.constraint.name == "In") -> {
                throw BusinessValidationException(BusinessConstants.FILTER_FIELD_VALIDATOR_MESSAGE)
            }

            else -> throw BusinessValidationException("${error.property}: ${error.constraint.name}")
        }
    }

}
