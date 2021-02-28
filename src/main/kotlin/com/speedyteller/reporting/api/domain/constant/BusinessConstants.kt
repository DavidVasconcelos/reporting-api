package com.speedyteller.reporting.api.domain.constant

class BusinessConstants {

    companion object {
        const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"

        const val HTTP_OK = 200

        const val STATUS_VALIDATOR_MESSAGE = "Invalid Status"

        const val OPERATION_VALIDATOR_MESSAGE = "Invalid Operation"

        const val PAYMENT_METHOD_VALIDATOR_MESSAGE = "Invalid PaymentMethod"

        const val ERROR_CODE_VALIDATOR_MESSAGE = "Invalid ErrorCode"

        const val FILTER_FIELD_VALIDATOR_MESSAGE = "Invalid FilterField"

        const val DATE_FORMAT_VALIDATOR_MESSAGE = "Invalid date format, use only the format: yyyy-MM-dd"

        const val REGEX_DATE_FORMAT_VALIDATOR =
            "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))"

    }
}
