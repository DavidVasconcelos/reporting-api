package com.speedyteller.reporting.api.cache

import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Component
class CustomKeyGenerator : KeyGenerator {

    override fun generate(target: Any, method: Method, vararg params: Any?): Any {
        val prefix = "${target.javaClass.simpleName}_${method.name}"

        return params.takeIf { it.isNotEmpty() }
            ?.joinToString(separator = "_")
            ?.let { joinedParams -> "${prefix}_$joinedParams" }
            ?: prefix
    }
}
