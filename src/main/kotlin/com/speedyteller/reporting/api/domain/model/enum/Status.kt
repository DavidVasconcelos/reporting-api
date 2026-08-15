package com.speedyteller.reporting.api.domain.model.enum

import java.util.Locale

enum class Status {
    APPROVED,
    WAITING,
    DECLINED,
    ERROR,
    ;

    companion object {
        fun getStatus(status: String) = entries.firstOrNull {
            it.name == status.trim().uppercase(
                Locale.getDefault(),
            )
        }
    }
}
