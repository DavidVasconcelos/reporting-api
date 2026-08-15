package com.speedyteller.reporting.api.common

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
