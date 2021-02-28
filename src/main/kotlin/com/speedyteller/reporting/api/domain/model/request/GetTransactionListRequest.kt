package com.speedyteller.reporting.api.domain.model.request

import com.speedyteller.reporting.api.domain.dto.request.GetTransactionListRequestDTO
import com.speedyteller.reporting.api.domain.enum.ErrorCode
import com.speedyteller.reporting.api.domain.enum.FilterField
import com.speedyteller.reporting.api.domain.enum.Operation
import com.speedyteller.reporting.api.domain.enum.PaymentMethod
import com.speedyteller.reporting.api.domain.enum.Status
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
}
