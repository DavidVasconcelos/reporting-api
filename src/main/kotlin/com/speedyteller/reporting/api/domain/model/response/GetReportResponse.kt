package com.speedyteller.reporting.api.domain.model.response

import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.COUNT
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.CURRENCY
import com.speedyteller.reporting.api.domain.constant.BusinessConstants.DataBaseFields.TOTAL
import java.math.BigDecimal

data class GetReportResponse(var count: Long? = null, var total: BigDecimal? = null, var currency: String? = null) {
    constructor(record: Array<Any>) : this() {
        this.count = record[COUNT] as? Long
        this.total = record[TOTAL] as? BigDecimal
        this.currency = record[CURRENCY] as? String
    }
}
