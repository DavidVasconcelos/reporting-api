package com.speedyteller.reporting.api.domain.model.extension

import com.speedyteller.reporting.api.domain.model.TransactionSummary
import com.speedyteller.reporting.api.extension.toStringPattern
import com.speedyteller.reporting.api.web.dto.response.GetTransactionSummaryResponseDTO
import com.speedyteller.reporting.api.web.dto.response.GetTransactionSummaryResponseDTO.Acquirer
import com.speedyteller.reporting.api.web.dto.response.GetTransactionSummaryResponseDTO.CustomerInfo
import com.speedyteller.reporting.api.web.dto.response.GetTransactionSummaryResponseDTO.FX
import com.speedyteller.reporting.api.web.dto.response.GetTransactionSummaryResponseDTO.FXMerchant
import com.speedyteller.reporting.api.web.dto.response.GetTransactionSummaryResponseDTO.IPN
import com.speedyteller.reporting.api.web.dto.response.GetTransactionSummaryResponseDTO.Merchant
import com.speedyteller.reporting.api.web.dto.response.GetTransactionSummaryResponseDTO.MerchantTransaction
import com.speedyteller.reporting.api.web.dto.response.GetTransactionSummaryResponseDTO.TransactionInfo

fun TransactionSummary.toDTO(): GetTransactionSummaryResponseDTO = GetTransactionSummaryResponseDTO(

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
