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
        fun getPaymentMethod(poaymentMethod: String) =
            values().firstOrNull { it.name == poaymentMethod.trim().toUpperCase() }
    }
}
