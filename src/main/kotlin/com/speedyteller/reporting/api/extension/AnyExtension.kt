package com.speedyteller.reporting.api.extension

import java.util.Optional

fun <T> Optional<T>.unwrap(): T? = orElse(null)
