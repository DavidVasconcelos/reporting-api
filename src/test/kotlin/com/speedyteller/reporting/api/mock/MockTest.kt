package com.speedyteller.reporting.api.mock

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.AgentInfo
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Component
class MockTest {

    fun getCustumer(): Customer {

        return Customer().apply {
            id = 1
            created_at = LocalDateTime.of(LocalDate.of(2015, 10, 9), LocalTime.of(12, 9, 10))
            updated_at = LocalDateTime.of(LocalDate.of(2015, 10, 9), LocalTime.of(12, 9, 10))
            number = "401288XXXXXX1881"
            expiryMonth = "6"
            expiryYear = "2017"
            email = "michael@gmail.com"
            birthday = LocalDateTime.of(LocalDate.of(1986, 3, 20), LocalTime.of(12, 9, 10))
            billingFirstName = "Michael"
            billingLastName = "Kara"
            billingAddress1 = "test address"
            billingCity = "Antalya"
            billingPostcode = "07070"
            billingCountry = "TR"
            shippingFirstName = "Michael"
            shippingLastName = "Kara"
            shippingAddress1 = "test address"
            shippingCity = "Antalya"
            shippingPostcode = "07070"
            shippingCountry = "TR"

        }
    }

    fun getTransactionResponse(): GetTransactionResponse {

        return GetTransactionResponse(
            fx = FXResponse(merchant = this.getFXMerchant()),
            customerInfo = this.getCustumer(),
            acquirer = this.getGetTransactionAcquirerResponse(),
            merchant = GetTransactionMerchantResponse(name = "Dev-Merchant"),
            transaction = GetTransactionMerchantTransactionResponse(merchant = this.getTransaction())
        )
    }

    fun getGetTransactionAcquirerResponse(): GetTransactionAcquirerResponse {

        return GetTransactionAcquirerResponse().apply {
            name = "Comitten Bank"
            code = "CB"
        }
    }

    fun getFXMerchant(): FXMerchant {

        return FXMerchant().apply {

            originalAmount = BigDecimal("100.00")
            originalCurrency = "EUR"
        }

    }

    fun getTransaction(): Transaction {

        val agentInfo =
            AgentInfo(id = 1, customerIp = "192.168.1.2", customerUserAgent = "Agent", merchantIp = "127.0.0.1")


        return Transaction().apply {
            referenceNo = "reference_5617ae66281ee"
            merchantId = 1
            status = "WAITING"
            channel = "API"
            chainId = "5617ae666b4cb"
            agentInfoId = 1
            operation = "DIRECT"
            fxTransactionId = 1
            updated_at = LocalDateTime.of(LocalDate.of(2015, 10, 9), LocalTime.of(12, 9, 12))
            created_at = LocalDateTime.of(LocalDate.of(2015, 10, 9), LocalTime.of(12, 9, 10))
            id = 1
            acquirerTransactionId = 1
            code = "00"
            message = "Waiting"
            transactionId = "1-1444392550-1"
            agent = agentInfo
            customerId = 1
            refundable = true
        }
    }

    fun getTransactionListResponse(): List<GetTransactionListResponse> {


        val fxResponse = FXMerchant().apply {

            originalAmount = BigDecimal("5.00")
            originalCurrency = "EUR"
        }

        val acquirerResponse = Acquirer().apply {
            id = 12
            name = "Mergen Bank"
            code = "MB"
            type = "CREDITCARD"
        }

        val customerResponse = GetTransactionListCustmerResponse().apply {
            number = "448574XXXXXX3395"
            email = "aykut.aras@bumin.com.tr"
            billingFirstName = "Aykut"
            billingLastName = "Aras"
        }

        val merchantResponse = GetTransactionListMerchantResponse().apply {
            id = 3
            name = "Dev-Merchant"
        }

        val transactionResponse = GetTransactionListTransactionResponse().apply {
            referenceNo = "api_560a4a9314208"
            status = "APPROVED"
            operation = "3DAUTH"
            message = "Auth3D is APPROVED"
            created_at = LocalDateTime.of(LocalDate.of(2015, 9, 29), LocalTime.of(8, 24, 42))
            transactionId = "2827-1443515082-3"
        }

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
                count = 1,
                total = BigDecimal("100.00"),
                currency = "EUR"
            ),
            GetReportResponse(
                count = 2,
                total = BigDecimal("375.00"),
                currency = "USD"
            )
        )
    }
}