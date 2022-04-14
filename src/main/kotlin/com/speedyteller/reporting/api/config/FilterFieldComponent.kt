package com.speedyteller.reporting.api.config

import com.speedyteller.reporting.api.domain.enum.FilterField
import com.speedyteller.reporting.api.exception.BusinessValidationException
import org.springframework.stereotype.Component
import java.util.Collections
import java.util.EnumMap

@Component
class FilterFieldComponent {

    private val fieldProviderMap: MutableMap<FilterField, String> =
        Collections.synchronizedMap(EnumMap(FilterField::class.java))

    init {
        fieldProviderMap[FilterField.TRANSACTION_UUID] = "tr.transaction_id"
        fieldProviderMap[FilterField.CUSTOMER_EMAIL] = "c.email"
        fieldProviderMap[FilterField.REFERENCE_NO] = "tr.reference_no"
        fieldProviderMap[FilterField.CUSTOM_DATA] = "tr.custom_data"
        fieldProviderMap[FilterField.CARD_PAN] = "c.number"
    }

    fun interpret(filterField: FilterField): String {
        return fieldProviderMap[filterField] ?: throw BusinessValidationException(ERROR_MESSAGE)
    }

    companion object {
        const val ERROR_MESSAGE = "Invalid FilterField"
    }
}
