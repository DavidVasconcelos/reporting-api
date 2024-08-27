package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.ACQUIRER_CODE
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.ACQUIRER_ID
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.ACQUIRER_NAME
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.ACQUIRER_TYPE
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.BILLING_FIRST_NAME
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.BILLING_LAST_NAME
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.CREATED_AT
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.EMAIL
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.MERCHANT_ID
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.MERCHANT_NAME
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.MESSAGE
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.NUMBER
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.OPERATION
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.ORIGINAL_AMOUNT
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.ORIGINAL_CURRENCY
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.RECEIVED
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.REFERENCE_NO
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.REFUNDABLE
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.STATUS
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.TRANSACTION_ID
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.LocalDateTime

data class GetTransactionList(
    var originalAmount: BigDecimal? = null,
    var originalCurrency: String? = null,
    var number: String? = null,
    var email: String? = null,
    var billingFirstName: String? = null,
    var billingLastName: String? = null,
    var merchantId: Long? = null,
    var merchantName: String? = null,
    var received: Boolean? = null,
    var referenceNo: String? = null,
    var status: String? = null,
    var operation: String? = null,
    var message: String? = null,
    var createdAt: LocalDateTime? = null,
    var transactionId: String? = null,
    var acquirerId: Long? = null,
    var acquirerName: String? = null,
    var acquirerCode: String? = null,
    var acquirerType: String? = null,
    var refundable: Boolean = false
) {
    constructor(record: Array<Any>) : this() {
        originalAmount = record[ORIGINAL_AMOUNT] as? BigDecimal
        originalCurrency = record[ORIGINAL_CURRENCY] as? String
        number = record[NUMBER] as? String
        email = record[EMAIL] as? String
        billingFirstName = record[BILLING_FIRST_NAME] as? String
        billingLastName = record[BILLING_LAST_NAME] as? String
        merchantId = record[MERCHANT_ID] as? Long
        merchantName = record[MERCHANT_NAME] as? String
        received = record[RECEIVED] as? Boolean
        referenceNo = record[REFERENCE_NO] as? String
        status = record[STATUS] as? String
        operation = record[OPERATION] as? String
        message = record[MESSAGE] as? String
        createdAt = (record[CREATED_AT] as? Timestamp)?.toLocalDateTime()
        transactionId = record[TRANSACTION_ID] as? String
        acquirerId = record[ACQUIRER_ID] as? Long
        acquirerName = record[ACQUIRER_NAME] as? String
        acquirerCode = record[ACQUIRER_CODE] as? String
        acquirerType = record[ACQUIRER_TYPE] as? String
        refundable = record[REFUNDABLE] as? Boolean ?: false
    }
}
