package com.speedyteller.reporting.api.domain.constant

class BusinessConstants {

    companion object {
        const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"

        const val HTTP_OK = 200

        const val STATUS_VALIDATOR_MESSAGE = "Status inválido"

        const val DATE_FORMAT_VALIDATOR_MESSAGE = "Formato inválido para a data, usar apenas o formato: yyyy-MM-dd"

        const val REGEX_DATE_FORMAT_VALIDATOR =
            "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))"

    }
}
