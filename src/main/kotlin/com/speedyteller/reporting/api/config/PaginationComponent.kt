package com.speedyteller.reporting.api.config

import com.speedyteller.reporting.api.domain.dto.page.CustomPageDTO
import org.springframework.stereotype.Component

@Component
class PaginationComponent {

    fun getPagination(pageSize: Int, page: Int, uri: String, data: List<Any>): CustomPageDTO {
        val nextPageUrl = uri.substringBeforeLast("").plus("${page.plus(PAGE_INITIAL_SIZE)}")
        val prevPageUrl =
            if (page > PAGE_INITIAL_SIZE) uri.substringBeforeLast("")
                .plus("${page.minus(PAGE_INITIAL_SIZE)}") else null
        val from = (page.minus(PAGE_INITIAL_SIZE)).times(pageSize).plus(PAGE_INITIAL_SIZE)
        val to = (from.minus(PAGE_INITIAL_SIZE)).plus(data.count())
        return CustomPageDTO(
            perPage = pageSize,
            currentPage = page,
            nextPageUrl = nextPageUrl,
            prevPageUrl = prevPageUrl,
            from = from,
            to = to,
            data = data
        )
    }

    companion object {
        const val PAGE_INITIAL_SIZE = 1
    }
}
