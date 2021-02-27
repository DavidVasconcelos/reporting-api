package com.speedyteller.reporting.api.domain.enum

enum class Status {

    APPROVED,
    WAITING,
    DECLINED,
    ERROR;

    companion object {
        fun getStatus(status: String) =  values().firstOrNull { it.name == status.trim().toUpperCase() }
    }
}
