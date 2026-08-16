package com.speedyteller.reporting.api.domain.model.enum

import java.util.Locale

enum class PaymentMethod {
    CREDITCARD,
    CUP,
    IDEAL,
    GIROPAY,
    MISTERCASH,
    STORED,
    PAYTOCARD,
    CEPBANK,
    CITADEL,
    ;

    companion object {
        fun getPaymentMethod(paymentMethod: String) =
            entries.firstOrNull { it.name == paymentMethod.trim().uppercase(Locale.getDefault()) }
    }
}
