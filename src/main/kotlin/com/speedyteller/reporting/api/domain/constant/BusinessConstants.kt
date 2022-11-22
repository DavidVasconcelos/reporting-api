package com.speedyteller.reporting.api.domain.constant

object BusinessConstants {

    object Patterns {
        const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
    }

    object ValidatorMessages {
        const val DATE_FORMAT_VALIDATOR_MESSAGE = "Invalid date format, use only the format: yyyy-MM-dd"
    }

    object RegexFormats {
        const val REGEX_DATE_FORMAT_VALIDATOR =
            "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))"
    }

    object DataBaseFields {
        const val ORIGINAL_AMOUNT = 0
        const val ORIGINAL_CURRENCY = 1
        const val NUMBER = 2
        const val EMAIL = 3
        const val BILLING_FIRST_NAME = 4
        const val BILLING_LAST_NAME = 5
        const val MERCHANT_ID = 6
        const val MERCHANT_NAME = 7
        const val RECEIVED = 8
        const val REFERENCE_NO = 9
        const val STATUS = 10
        const val OPERATION = 11
        const val MESSAGE = 12
        const val CREATED_AT = 13
        const val TRANSACTION_ID = 14
        const val ACQUIRER_ID = 15
        const val ACQUIRER_NAME = 16
        const val ACQUIRER_CODE = 17
        const val ACQUIRER_TYPE = 18
        const val REFUNDABLE = 19
        const val COUNT = 0
        const val TOTAL = 1
        const val CURRENCY = 2
    }

    object Queries {
        const val QUERY_GET_TRANSACTION_LIST = """SELECT ft.original_amount as originalAmount,
                       ft.original_currency as originalCurrency,
                       c.number,
                       c.email,
                       c.billing_first_name as billingFirstName,
                       c.billing_last_name as billingLastName,
                       m.id as merchantId,
                       m.name as merchantName,
                       ipn.received,
                       tr.reference_no as referenceNo,
                       tr.status,
                       tr.operation,
                       tr.message,
                       tr.created_at,
                       tr.transaction_id as transactionId,
                       a.id as acquirerId,
                       a.name as acquirerName,
                       a.code as acquirerCode,
                       a.type as acquirerType,
                       tr.refundable
                FROM speedyteller.transaction tr left join speedyteller.fx_transaction ft on tr.fx_transaction_td = ft.id
                left join speedyteller.customer c on tr.customer_id = c.id
                left join speedyteller.merchant m on tr.merchant_id = m.id
                left join speedyteller.instant_payment_notification ipn on tr.transaction_id = ipn.transaction_id
                left join speedyteller.acquirer a on tr.acquirer_transaction_id = a.id
                WHERE 1=1 """
        const val QUERY_GET_REPORT = """SELECT COUNT(tr.id) as count,
                                               SUM(ft.original_amount) as total,
                                               ft.original_currency currency
                                        FROM speedyteller.transaction tr join speedyteller.fx_transaction ft on 
                                        tr.fx_transaction_td = ft.id
                                        WHERE 1=1 """
    }
}
