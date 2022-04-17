package com.speedyteller.reporting.api.support.json

import org.json.JSONObject

class JsonScope {
    var json: JSONObject

    /**
     * ExplicitJson methods holder
     *
     * This class is a holder to the builder methods used inside ExplicitJson body,
     * when instatiate it updates de [json] property with the [applyExplicitJson] commands.
     *
     * @property json JSONObject to be updated.
     * @property applyExplicitJson ExplicitJson to be executed.
     * @constructor Updates [json] when running [applyExplicitJson]
     */
    constructor(json: JSONObject, applyExplicitJson: ExplicitJson) {
        this.json = json
        applyExplicitJson(json)
    }

    /**
     * Add a key/[value] pair to [json]
     * @param T the type of the value
     * @return nothing.
     */
    infix fun<T> String.to(value: T?) {
        json.put(this, value ?: JSONObject.NULL)
    }

    /**
     * Relates [ExplicitJson] to a key, updating the [json]
     * @return nothing.
     */
    infix fun String.to(explicitJson: ExplicitJson) {
        val retrievedJson = json.optJSONObject(this)
        if (retrievedJson != null) {
            json.put(this, retrievedJson.merge(explicitJson))
        } else {
            json.put(this, jsonObject(explicitJson))
        }
    }
}
