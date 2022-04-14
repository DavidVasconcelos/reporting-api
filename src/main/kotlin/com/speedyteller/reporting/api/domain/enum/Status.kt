package com.speedyteller.reporting.api.domain.enum

import java.util.Locale

enum class Status {
    APPROVED,
    WAITING,
    DECLINED,
    ERROR;

    companion object {
        fun getStatus(status: String) = values().firstOrNull { it.name == status.trim().uppercase(Locale.getDefault()) }
    }
}
