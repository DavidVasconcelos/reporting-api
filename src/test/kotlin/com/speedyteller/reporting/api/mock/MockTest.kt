package com.speedyteller.reporting.api.mock

import com.google.gson.Gson
import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.Transaction
import com.speedyteller.reporting.api.domain.model.response.FXMerchant
import com.speedyteller.reporting.api.domain.model.response.FXResponse
import com.speedyteller.reporting.api.domain.model.response.GetReportResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionAcquirerResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListCustmerResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListIPNResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListMerchantResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListMerchantTransactionResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionListTransactionResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionMerchantResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionMerchantTransactionResponse
import com.speedyteller.reporting.api.domain.model.response.GetTransactionResponse
import org.springframework.stereotype.Component
import java.math.BigDecimal

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

    fun getTransactionListResponse() : List<GetTransactionListResponse> {

        val gson = Gson()

        val fxJson = """{
                          "merchant": {
                            "originalAmount": 5,
                            "originalCurrency": "EUR"
                          }
                        }"""

        val fxResponse = gson.fromJson(fxJson, FXMerchant::class.java)

        val acquirerJson = """{
                              "acquirer": {
                                "id": 12,
                                "name": "Mergen Bank",
                                "code": "MB",
                                "type": "CREDITCARD"
                              }
                            }"""

        val acquirerResponse = gson.fromJson(acquirerJson, Acquirer::class.java)

        val customerJson = """{
          "customerInfo": {
            "number": " 448574XXXXXX3395",
            "email": "aykut.aras@bumin.com.tr",
            "billingFirstName": "Aykut",
            "billingLastName": "Aras"
          }
        }"""

        val customerResponse = gson.fromJson(customerJson, GetTransactionListCustmerResponse::class.java)

        val merchantJson = """{
          "merchant": {
            "id" : 3,
            "name": "Dev-Merchant"
          }
        }"""

        val merchantResponse = gson.fromJson(merchantJson, GetTransactionListMerchantResponse::class.java)

        val transactionJson = """{
                                  "merchant": {
                                    "referenceNo": "api_560a4a9314208",
                                    "status": " APPROVED",
                                    "operation": "3DAUTH",
                                    "message": "Auth3D is APPROVED",
                                    "created_at": " 2015-09-29 08:24:42",
                                    "transactionId": "2827-1443515082-3"
                                  }
                                }"""

        val transactionResponse = gson.fromJson(transactionJson, GetTransactionListTransactionResponse::class.java)


        val getTransactionListResponse = GetTransactionListResponse(
            fx = FXResponse(merchant = fxResponse),
            customerInfo = customerResponse,
            merchant = merchantResponse,
            ipn = GetTransactionListIPNResponse(received = true),
            transaction = GetTransactionListMerchantTransactionResponse(merchant = transactionResponse),
            acquirer = acquirerResponse,
            refundable = true
        )

        return mutableListOf(getTransactionListResponse)
    }

    fun getReportResponse(): List<GetReportResponse> {

        return mutableListOf(
            GetReportResponse(
                count = 283,
                total = BigDecimal("28300"),
                currency = "USD"
            ),
            GetReportResponse(
                count = 280,
                total = BigDecimal("1636515"),
                currency = "EUR"
            )
        )
    }
}