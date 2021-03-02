package com.speedyteller.reporting.api.mock

import com.google.gson.Gson
import com.speedyteller.reporting.api.domain.model.Customer
import org.springframework.stereotype.Component

@Component
class MockTest {

    fun getCustumer(): Customer {

        val gson = Gson()

        val json = """{
                      "customer": {
                        "id": 1,
                        "created_at": " 2015-10-09 12:09:10",
                        "updated_at": " 2015-10-09 12:09:10",
                        "deleted_at": null,
                        "number": "401288XXXXXX1881",
                        "expiryMonth": "6",
                        "expiryYear": " 2017",
                        "startMonth": null,
                        "startYear": null,
                        "issueNumber": null,
                        "email": "michael@gmail.com",
                        "birthday": "1986-03-20 12:09:10",
                        "gender": null,
                        "billingTitle": null,
                        "billingFirstName": "Michael",
                        "billingLastName": "Kara",
                        "billingCompany": null,
                        "billingAddress1": "test address",
                        "billingAddress2": null,
                        "billingCity": "Antalya",
                        "billingPostcode": "07070",
                        "billingState": null,
                        "billingCountry": " TR",
                        "billingPhone": null,
                        "billingFax": null,
                        "shippingTitle": null,
                        "shippingFirstName": "Michael",
                        "shippingLastName": "Kara",
                        "shippingCompany": null,
                        "shippingAddress1": "test address",
                        "shippingAddress2": null,
                        "shippingCity": "Antalya",
                        "shippingPostcode": "07070",
                        "shippingState": null,
                        "shippingCountry": "TR",
                        "shippingPhone": null,
                        "shippingFax": null
                      }
                    }"""

        return gson.fromJson(json, Customer::class.java)
    }
}