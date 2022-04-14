package com.speedyteller.reporting.api.extension

import java.util.Locale

fun String.toCapital(): String = this.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}
