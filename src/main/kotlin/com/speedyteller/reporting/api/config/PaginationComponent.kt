package com.speedyteller.reporting.api.config

import com.speedyteller.reporting.api.domain.dto.page.CustomPageDTO
import org.springframework.stereotype.Component

@Component
class PaginationComponent {

    fun getPagination(pageSize: Int, page: Int, uri: String, data: List<Any>): CustomPageDTO {

        val nextPageUrl = uri.substringBeforeLast("").plus("${page.plus(ONE)}")
        val prevPageUrl = if (page > ONE) uri.substringBeforeLast("").plus("${page.minus(ONE)}") else null
        val from = (page.minus(ONE)).times(pageSize).plus(ONE)
        val to = (from.minus(ONE)).plus(data.count())

        return CustomPageDTO(
            per_page = pageSize,
            current_page = page,
            next_page_url = nextPageUrl,
            prev_page_url = prevPageUrl,
            from = from,
            to = to,
            data = data
        )
    }

    companion object {
        const val ONE = 1
    }
}
