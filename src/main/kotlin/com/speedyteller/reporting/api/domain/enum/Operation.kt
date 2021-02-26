package com.speedyteller.reporting.api.domain.enum

enum class Operation(val operation: String) {

    DIRECT("DIRECT"),
    REFUND("REFUND"),
    THREE_D("3D"),
    THREE_D_AUTH("3DAUTH"),
    STORED("STORED");

    companion object {
        fun getOperation(operation: String) = values().firstOrNull { it.operation == operation.trim().toUpperCase() }
    }
}
