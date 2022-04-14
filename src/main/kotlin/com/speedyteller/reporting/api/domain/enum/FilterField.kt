package com.speedyteller.reporting.api.domain.enum

enum class FilterField(val filterField: String) {
    TRANSACTION_UUID("Transaction UUID"),
    CUSTOMER_EMAIL("Customer Email"),
    REFERENCE_NO("Reference No"),
    CUSTOM_DATA("Custom Data"),
    CARD_PAN("Card PAN");

    companion object {
        fun getFilterField(filterField: String) =
            values().firstOrNull { it.filterField == filterField.trim() }
    }
}

