package com.speedyteller.reporting.api.support.json

import org.json.JSONArray
import org.json.JSONObject

/**
 * Shortcut to instantiate [ExplicitJson]s
 * meant to be used with a free block/lambda as argument.
 * @param explicitJson A lambda to be evaluated as an [ExplicitJson]
 * @return [ExplicitJson].
 */
fun json(explicitJson: ExplicitJson) = explicitJson

/**
 * Shortcut to generate a JSONObject from an [ExplicitJson]
 * @param explicitJson A lambda to be evaluated as an [ExplicitJson]
 * @return [JSONObject] related to [ExplicitJson].
 */
fun jsonObject(explicitJson: ExplicitJson) = explicitJson.toObject()

/**
 * Shortcut to serialize the [ExplicitJson] as a [String]
 * @param explicitJson A lambda to be evaluated as an [ExplicitJson]
 * @return [String] serialized [ExplicitJson].
 */
fun serializedJson(explicitJson: ExplicitJson) = explicitJson.serialize()

/**
 * [JSONObject] extension function to apply [ExplicitJson] attributions to [JSONObject]
 * @param applyExplicitJson A lambda to be evaluated as an [ExplicitJson]
 * @return [JSONObject] updated with [ExplicitJson] attributions.
 */
infix fun JSONObject.merge(applyExplicitJson: ExplicitJson): JSONObject {
    JsonScope(this, applyExplicitJson)
    return this
}

/**
 * Function that allows to idiomatically generate an [JSONArray] from a list of [ExplicitJson]s
 * @param explicitJsons A list os [ExplicitJson]s as vararg
 * @return [JSONArray] with [JSONObject]s generated from the [ExplicitJson]s
 */
fun jsonList(vararg explicitJsons: ExplicitJson) = JSONArray(explicitJsons.map { JSONObject().merge(it) })

/**
 * Function that allows to idiomatically generate an [JSONArray] from a list of [Any] object
 * @param items A list os [Any] as vararg
 * @return [JSONArray]
 */
fun jsonList(vararg items: Any?) = JSONArray(items)
