package com.speedyteller.reporting.api.mock

import com.google.gson.Gson
import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.Transaction
import com.speedyteller.reporting.api.domain.model.response.FXMerchant
import com.speedyteller.reporting.api.domain.model.response.FXResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionAcquirerResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionMerchantResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionMerchantTransactionResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionResponse
import org.springframework.stereotype.Component

@Component
class MockTest {

    fun getCustumer(): Customer {

        val gson = Gson()

        val json = """{
                      "customer": {
                        "id": 1,
                        "created_at": "2015-10-09 12:09:10",
                        "updated_at": "2015-10-09 12:09:10",
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

    fun getTransactionResponse(): GetTransactionResponse {

        val gson = Gson()

        val fxJson = """{
                          "merchant": {
                            "originalAmount": 100,
                            "originalCurrency": "EUR"
                          }
                        }"""

        val fxResponse = gson.fromJson(fxJson, FXMerchant::class.java)

        val acquirerJson = """{
          "acquirer": {
            "name": "Mergen Bank",
            "code": "MB"
          }
        }"""

        val acquirerResponse = gson.fromJson(acquirerJson, GetTransactionAcquirerResponse::class.java)

        val merchantJson = """{
          "merchant": {
            " name": "Dev-Merchant"
          }
        }"""

        val merchantResponse = gson.fromJson(merchantJson, GetTransactionMerchantResponse::class.java)

        val transactionJson = """{
                                  "transaction": {
                                    "referenceNo": "reference_5617ae66281ee",
                                    "merchantId": 1,
                                    "status": " WAITING",
                                    "channel": "API",
                                    "customData": null,
                                    "chainId": "5617ae666b4cb",
                                    "agentInfoId": 1,
                                    "operation": "DIRECT",
                                    "fxTransactionId": 1,
                                    "updated_at": "2015-10-09 12:09:12",
                                    "created_at": "2015-10-09 12:09:10",
                                    "id": 1,
                                    "acquirerTransactionId": 1,
                                    "code": "00",
                                    "message": "Waiting",
                                    "transactionId": "1-1444392550-1"                                    
                                  }
                                }"""

        val transactionResponse = gson.fromJson(transactionJson, Transaction::class.java)

        return GetTransactionResponse(
            fx = FXResponse(merchant = fxResponse),
            customerInfo = this.getCustumer(),
            acquirer = acquirerResponse,
            merchant = merchantResponse,
            transaction = GetTransactionMerchantTransactionResponse(merchant = transactionResponse)
        )
    }
}