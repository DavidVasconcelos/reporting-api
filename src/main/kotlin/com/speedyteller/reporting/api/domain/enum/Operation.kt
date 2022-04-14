package com.speedyteller.reporting.api.domain.enum

import java.util.Locale

enum class Operation(val operation: String) {
    DIRECT("DIRECT"),
    REFUND("REFUND"),
    THREE_D("3D"),
    THREE_D_AUTH("3DAUTH"),
    STORED("STORED");

    companion object {
        fun getOperation(operation: String) = values().firstOrNull { it.operation == operation.trim()
            .uppercase(Locale.getDefault()) }
    }
}
