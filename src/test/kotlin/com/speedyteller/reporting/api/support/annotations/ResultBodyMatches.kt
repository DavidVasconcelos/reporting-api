package com.speedyteller.reporting.api.support.annotations

import com.speedyteller.reporting.api.support.json.ExplicitJson
import com.speedyteller.reporting.api.support.json.serialize
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.test.web.servlet.ResultActions

fun ResultActions.andResultBodyMatches(strict: Boolean = true, json: ExplicitJson): ResultActions =
    this.andDo { mvcResult ->
        val content = mvcResult.response.contentAsString
        JSONAssert.assertEquals(json.serialize(), content, strict)
    }

fun ResultActions.andResultBodyMatches(json: String): ResultActions =
    this.andDo { mvcResult ->
        val content = mvcResult.response.contentAsString
        JSONAssert.assertEquals(json, content, JSONCompareMode.LENIENT)
    }
