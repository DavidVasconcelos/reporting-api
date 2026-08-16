package com.speedyteller.reporting.api.domain.model.extension

import com.speedyteller.reporting.api.domain.model.TransactionSummary
import com.speedyteller.reporting.api.extension.toStringPattern
import com.speedyteller.reporting.api.web.dto.response.TransactionSummaryResponseDTO
import com.speedyteller.reporting.api.web.dto.response.TransactionSummaryResponseDTO.Acquirer
import com.speedyteller.reporting.api.web.dto.response.TransactionSummaryResponseDTO.CustomerInfo
import com.speedyteller.reporting.api.web.dto.response.TransactionSummaryResponseDTO.FX
import com.speedyteller.reporting.api.web.dto.response.TransactionSummaryResponseDTO.FXMerchant
import com.speedyteller.reporting.api.web.dto.response.TransactionSummaryResponseDTO.IPN
import com.speedyteller.reporting.api.web.dto.response.TransactionSummaryResponseDTO.Merchant
import com.speedyteller.reporting.api.web.dto.response.TransactionSummaryResponseDTO.MerchantTransaction
import com.speedyteller.reporting.api.web.dto.response.TransactionSummaryResponseDTO.TransactionInfo

fun TransactionSummary.toDTO(): TransactionSummaryResponseDTO = TransactionSummaryResponseDTO(

    acquirer = Acquirer(
        id = this.acquirerId,
        name = this.acquirerName,
        code = this.acquirerCode,
        type = this.acquirerType,
    ),

    fx = FX(
        merchant = FXMerchant(
            originalAmount = this.originalAmount,
            originalCurrency = this.originalCurrency,
        ),
    ),

    customerInfo = CustomerInfo(
        number = this.number,
        email = this.email,
        billingFirstName = this.billingFirstName,
        billingLastName = this.billingLastName,
    ),

    merchant = Merchant(
        id = this.merchantId,
        name = this.merchantName,
    ),

    ipn = IPN(
        received = this.received,
    ),

    refundable = this.refundable,

    transaction = MerchantTransaction(
        merchant = TransactionInfo(
            referenceNo = this.referenceNo,
            status = this.status,
            operation = this.operation,
            message = this.message,
            createdAt = this.createdAt?.toStringPattern(),
            transactionId = this.transactionId,
        ),
    ),
)
