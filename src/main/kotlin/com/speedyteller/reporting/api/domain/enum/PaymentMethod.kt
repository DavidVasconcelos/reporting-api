package com.speedyteller.reporting.api.domain.enum

enum class PaymentMethod {

    CREDITCARD,
    CUP,
    IDEAL,
    GIROPAY,
    MISTERCASH,
    STORED,
    PAYTOCARD,
    CEPBANK,
    CITADEL;

    companion object {
        fun getPaymentMethod(paymentMethod: String) =
            values().firstOrNull { it.name == paymentMethod.trim().toUpperCase() }
    }
}
