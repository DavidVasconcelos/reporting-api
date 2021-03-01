package com.speedyteller.reporting.api.domain.constant

class BusinessConstants {

    companion object {
        const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"

        const val HTTP_OK = 200

        const val STATUS_VALIDATOR_MESSAGE = "Invalid Status"

        const val OPERATION_VALIDATOR_MESSAGE = "Invalid Operation"

        const val PAYMENT_METHOD_VALIDATOR_MESSAGE = "Invalid PaymentMethod"

        const val ERROR_CODE_VALIDATOR_MESSAGE = "Invalid ErrorCode"

        const val FILTER_FIELD_VALIDATOR_MESSAGE = "Invalid FilterField"

        const val DATE_FORMAT_VALIDATOR_MESSAGE = "Invalid date format, use only the format: yyyy-MM-dd"

        const val REGEX_DATE_FORMAT_VALIDATOR =
            "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))"

        const val QUER_GET_TRANSACTION_LIST = """SELECT ft.original_amount as originalAmount,
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
                FROM transaction tr left join fx_transaction ft on tr.fx_transaction_td = ft.id
                left join customer c on tr.customer_id = c.id
                left join merchant m on tr.merchant_id = m.id
                left join instant_payment_notification ipn on tr.transaction_id = ipn.transaction_id
                left join acquirer a on tr.acquirer_transaction_id = a.id
                WHERE 1=1 """

    }
}
