package com.speedyteller.reporting.api.domain.dto.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.speedyteller.reporting.api.domain.constant.BusinessConstants
import com.speedyteller.reporting.api.domain.enum.ErrorCode
import com.speedyteller.reporting.api.domain.enum.FilterField
import com.speedyteller.reporting.api.domain.enum.Operation
import com.speedyteller.reporting.api.domain.enum.PaymentMethod
import com.speedyteller.reporting.api.domain.enum.Status
import com.speedyteller.reporting.api.validator.ValidErrorCode
import com.speedyteller.reporting.api.validator.ValidFilterField
import com.speedyteller.reporting.api.validator.ValidOperation
import com.speedyteller.reporting.api.validator.ValidPaymentMethod
import com.speedyteller.reporting.api.validator.ValidStatus
import javax.validation.constraints.Pattern

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class GetTransactionListRequestDTO {

    @Pattern(
        regexp = BusinessConstants.REGEX_DATE_FORMAT_VALIDATOR,
        message = BusinessConstants.DATE_FORMAT_VALIDATOR_MESSAGE
    )
    var fromDate: String? = null

    @Pattern(
        regexp = BusinessConstants.REGEX_DATE_FORMAT_VALIDATOR,
        message = BusinessConstants.DATE_FORMAT_VALIDATOR_MESSAGE
    )
    var toDate: String? = null

    @field:ValidStatus(enumClass = Status::class)
    var status: String? = null

    @field:ValidOperation(enumClass = Operation::class)
    var operation: String? = null

    @field:ValidPaymentMethod(enumClass = PaymentMethod::class)
    var paymentMethod: String? = null

    @field:ValidErrorCode(enumClass = ErrorCode::class)
    var errorCode: String? = null

    @field:ValidFilterField(enumClass = FilterField::class)
    var filterField: String? = null
    var filterValue: String? = null

    var merchantId : Int? = null
    var acquirerId: Int? = null


    constructor()
}