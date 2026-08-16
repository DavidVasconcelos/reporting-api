package com.speedyteller.reporting.api.mock

import com.speedyteller.reporting.api.domain.model.Acquirer
import com.speedyteller.reporting.api.domain.model.AgentInfo
import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.domain.model.FXTransaction
import com.speedyteller.reporting.api.domain.model.Merchant
import com.speedyteller.reporting.api.domain.model.Transaction
import com.speedyteller.reporting.api.domain.model.TransactionSummary
import com.speedyteller.reporting.api.domain.model.response.GetReportResponse
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Component
class MockTest {

    fun getCustumer(): Customer = Customer().apply {
        id = 1
        createdAt = LocalDateTime.of(LocalDate.of(2015, 10, 9), LocalTime.of(12, 9, 10))
        updatedAt = LocalDateTime.of(LocalDate.of(2015, 10, 9), LocalTime.of(12, 9, 10))
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

    fun getTransaction(): Transaction {
        val agentInfo = AgentInfo(
            id = 1,
            customerIp = "192.168.1.2",
            customerUserAgent = "Agent",
            merchantIp = "127.0.0.1",
        )

        return Transaction().apply {
            referenceNo = "reference_5617ae66281ee"
            merchantId = 1
            status = "WAITING"
            channel = "API"
            chainId = "5617ae666b4cb"
            agentInfoId = 1
            operation = "DIRECT"
            fxTransactionId = 1
            updatedAt = LocalDateTime.of(LocalDate.of(2015, 10, 9), LocalTime.of(12, 9, 12))
            createdAt = LocalDateTime.of(LocalDate.of(2015, 10, 9), LocalTime.of(12, 9, 10))
            id = 1
            acquirerTransactionId = 2
            code = "00"
            message = "Waiting"
            transactionId = "1-1444392550-1"
            agent = agentInfo
            customerId = 1
            refundable = true

            customer = getCustumer()

            merchant = Merchant().apply {
                id = 1
                name = "Dev-Merchant"
            }

            acquirer = Acquirer().apply {
                id = 2
                name = "Comitten Bank"
                code = "CB"
                type = "PAYTOCARD"
            }

            fxTransaction = FXTransaction().apply {
                id = 1
                originalAmount = BigDecimal("100.00")
                originalCurrency = "EUR"
            }
        }
    }

    fun getTransactionSummaryList(): List<TransactionSummary> {
        val summary = TransactionSummary(
            originalAmount = BigDecimal("5.00"),
            originalCurrency = "EUR",

            number = "448574XXXXXX3395",
            email = "aykut.aras@bumin.com.tr",
            billingFirstName = "Aykut",
            billingLastName = "Aras",

            merchantId = 3L,
            merchantName = "Dev-Merchant",

            received = true,

            referenceNo = "api_560a4a9314208",
            status = "APPROVED",
            operation = "3DAUTH",
            message = "Auth3D is APPROVED",
            createdAt = LocalDateTime.of(LocalDate.of(2015, 9, 29), LocalTime.of(8, 24, 42)),
            transactionId = "2827-1443515082-3",
            refundable = true,

            acquirerId = 1L,
            acquirerName = "Mergen Bank",
            acquirerCode = "MB",
            acquirerType = "CREDITCARD",
        )

        return listOf(summary)
    }

    fun getReportResponse(): List<GetReportResponse> = mutableListOf(
        GetReportResponse(
            count = 1,
            total = BigDecimal("100.00"),
            currency = "EUR",
        ),
        GetReportResponse(
            count = 2,
            total = BigDecimal("375.00"),
            currency = "USD",
        ),
    )
}
