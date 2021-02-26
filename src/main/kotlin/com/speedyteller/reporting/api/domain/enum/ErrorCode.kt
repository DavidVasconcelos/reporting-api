package com.speedyteller.reporting.api.domain.enum

enum class ErrorCode(val errorCode: String) {

    DO_NOT_HONOR("Do not honor"),
    INVALID_TRANSACTION("Invalid Transaction"),
    INVALID_CARD("Invalid Card"),
    NOT_SUFFICIENT_FUNDS("Not sufficient funds"),
    INCORRECT_PIN("Incorrect PIN"),
    INVALID_COUNTRY_ASSOCIATION("Invalid country association"),
    CURRENCY_NOT_ALLOWED("Currency not allowed"),
    THREE_D_SECURE_TRANSPORT_ERROR("3-D Secure Transport Error"),
    TRANSACTION_NOT_PERMITTED_TO_CARDHOLDER("Transaction not permitted to cardholder");

    companion object {
        fun getErrorCode(errorCode: String) =  values().firstOrNull { it.errorCode == errorCode.trim().toUpperCase() }
    }
}
