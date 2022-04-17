package com.speedyteller.reporting.api.support.json

import org.json.JSONObject

/**
 * Abstraction to a block/lambda that has access to [JsonScope] instance methods
 * and behaves like a builder when passed as argument to [JsonScope] constructor.
 */
typealias ExplicitJson = JsonScope.(JSONObject) -> Unit

/**
 * Generate an [ExplicitJson] merging another two [ExplicitJson]
 * @param anotherExplicitJson [ExplicitJson] to be merged
 * @return [ExplicitJson].
 */
infix fun ExplicitJson.merge(anotherExplicitJson: ExplicitJson): ExplicitJson {
    val actualExplicitJson = this
    return { actualExplicitJson(it); anotherExplicitJson(it) }
}

/**
 * Cast [ExplicitJson] into [String]
 * @return [String] serialized [ExplicitJson].
 */
fun ExplicitJson.serialize(): String = jsonObject(this).toString()

/**
 * Cast [ExplicitJson] into [JSONObject]
 * @return [JSONObject]
 */
fun ExplicitJson.toObject(): JSONObject = JSONObject().merge(this)

/**
 * Remove one or more keys from an [ExplicitJson].
 * @param keys one or more keys to be removed.
 * @return [ExplicitJson].
 */
fun ExplicitJson.remove(vararg keys: String): ExplicitJson {
    val actualExplicitJson = this
    return { json ->
        actualExplicitJson(json)
        keys.forEach { key -> json.remove(key) }
    }
}
