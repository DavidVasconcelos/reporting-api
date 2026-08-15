package com.speedyteller.reporting.api.domain.model.extension

import com.speedyteller.reporting.api.domain.model.Transaction
import com.speedyteller.reporting.api.extension.toStringPattern
import com.speedyteller.reporting.api.web.dto.response.GetTransactionResponseDTO
import com.speedyteller.reporting.api.web.dto.response.GetTransactionResponseDTO.Acquirer
import com.speedyteller.reporting.api.web.dto.response.GetTransactionResponseDTO.Agent
import com.speedyteller.reporting.api.web.dto.response.GetTransactionResponseDTO.CustomerInfo
import com.speedyteller.reporting.api.web.dto.response.GetTransactionResponseDTO.FX
import com.speedyteller.reporting.api.web.dto.response.GetTransactionResponseDTO.FXMerchant
import com.speedyteller.reporting.api.web.dto.response.GetTransactionResponseDTO.Merchant
import com.speedyteller.reporting.api.web.dto.response.GetTransactionResponseDTO.MerchantTransaction
import com.speedyteller.reporting.api.web.dto.response.GetTransactionResponseDTO.TransactionInfo

fun Transaction.toDTO(): GetTransactionResponseDTO = GetTransactionResponseDTO(
    acquirer = this.toAcquirer(),
    customerInfo = this.toCustomerInfo(),
    fx = this.toFX(),
    merchant = this.toMerchant(),
    transaction = this.toMerchantTransaction(),
)

private fun Transaction.toAcquirer() = Acquirer(
    name = this.acquirer?.name,
    code = this.acquirer?.code,
)

private fun Transaction.toCustomerInfo() = CustomerInfo().apply {
    this.id = this@toCustomerInfo.customer?.id
    this.createdAt = this@toCustomerInfo.customer?.createdAt?.toStringPattern()
    this.updatedAt = this@toCustomerInfo.customer?.updatedAt?.toStringPattern()
    this.deletedAt = this@toCustomerInfo.customer?.deletedAt?.toStringPattern()
    this.number = this@toCustomerInfo.customer?.number
    this.expiryMonth = this@toCustomerInfo.customer?.expiryMonth
    this.expiryYear = this@toCustomerInfo.customer?.expiryYear
    this.startMonth = this@toCustomerInfo.customer?.startMonth
    this.startYear = this@toCustomerInfo.customer?.startYear
    this.issueNumber = this@toCustomerInfo.customer?.issueNumber
    this.email = this@toCustomerInfo.customer?.email
    this.birthday = this@toCustomerInfo.customer?.birthday?.toStringPattern()
    this.gender = this@toCustomerInfo.customer?.gender
    this.billingTitle = this@toCustomerInfo.customer?.billingTitle
    this.billingFirstName = this@toCustomerInfo.customer?.billingFirstName
    this.billingLastName = this@toCustomerInfo.customer?.billingLastName
    this.billingCompany = this@toCustomerInfo.customer?.billingCompany
    this.billingAddress1 = this@toCustomerInfo.customer?.billingAddress1
    this.billingAddress2 = this@toCustomerInfo.customer?.billingAddress2
    this.billingCity = this@toCustomerInfo.customer?.billingCity
    this.billingPostcode = this@toCustomerInfo.customer?.billingPostcode
    this.billingState = this@toCustomerInfo.customer?.billingState
    this.billingCountry = this@toCustomerInfo.customer?.billingCountry
    this.billingPhone = this@toCustomerInfo.customer?.billingPhone
    this.billingFax = this@toCustomerInfo.customer?.billingFax
    this.shippingTitle = this@toCustomerInfo.customer?.shippingTitle
    this.shippingFirstName = this@toCustomerInfo.customer?.shippingFirstName
    this.shippingLastName = this@toCustomerInfo.customer?.shippingLastName
    this.shippingCompany = this@toCustomerInfo.customer?.shippingCompany
    this.shippingAddress1 = this@toCustomerInfo.customer?.shippingAddress1
    this.shippingAddress2 = this@toCustomerInfo.customer?.shippingAddress2
    this.shippingCity = this@toCustomerInfo.customer?.shippingCity
    this.shippingPostcode = this@toCustomerInfo.customer?.shippingPostcode
    this.shippingState = this@toCustomerInfo.customer?.shippingState
    this.shippingCountry = this@toCustomerInfo.customer?.shippingCountry
    this.shippingPhone = this@toCustomerInfo.customer?.shippingPhone
    this.shippingFax = this@toCustomerInfo.customer?.shippingFax
}

private fun Transaction.toFX() = FX(
    merchant = FXMerchant(
        originalAmount = this.fxTransaction?.originalAmount,
        originalCurrency = this.fxTransaction?.originalCurrency,
    ),
)

private fun Transaction.toMerchant() = Merchant(
    name = this.merchant?.name,
)

private fun Transaction.toMerchantTransaction() = MerchantTransaction(
    merchant = TransactionInfo().apply {
        this.id = this@toMerchantTransaction.id
        this.referenceNo = this@toMerchantTransaction.referenceNo
        this.merchantId = this@toMerchantTransaction.merchantId
        this.status = this@toMerchantTransaction.status
        this.channel = this@toMerchantTransaction.channel
        this.customData = this@toMerchantTransaction.customData
        this.chainId = this@toMerchantTransaction.chainId
        this.agentInfoId = this@toMerchantTransaction.agentInfoId
        this.operation = this@toMerchantTransaction.operation
        this.fxTransactionId = this@toMerchantTransaction.fxTransactionId
        this.updatedAt = this@toMerchantTransaction.updatedAt?.toStringPattern()
        this.createdAt = this@toMerchantTransaction.createdAt?.toStringPattern()
        this.acquirerTransactionId = this@toMerchantTransaction.acquirerTransactionId
        this.code = this@toMerchantTransaction.code
        this.message = this@toMerchantTransaction.message
        this.transactionId = this@toMerchantTransaction.transactionId
        this.customerId = this@toMerchantTransaction.customerId
        this.refundable = this@toMerchantTransaction.refundable
        this.errorCode = this@toMerchantTransaction.errorCode
        this.agent = this@toMerchantTransaction.agent?.let { domainAgent ->
            Agent().apply {
                this.id = domainAgent.id
                this.customerIp = domainAgent.customerIp
                this.customerUserAgent = domainAgent.customerUserAgent
                this.merchantIp = domainAgent.merchantIp
            }
        }
    },
)
