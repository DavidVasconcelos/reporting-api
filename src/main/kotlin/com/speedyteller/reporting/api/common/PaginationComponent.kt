package com.speedyteller.reporting.api.common

import com.speedyteller.reporting.api.web.dto.page.CustomPageDTO
import org.springframework.stereotype.Component
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder

@Component
class PaginationComponent {

    fun <T : Any> getPagination(pageSize: Int, page: Int, uri: String, data: List<T>): CustomPageDTO {
        val from = ((page - 1) * pageSize) + 1
        val to = (from - 1) + data.size

        val baseBuilder = UriComponentsBuilder.fromUriString(uri)

        val nextPageUrl = baseBuilder.cloneBuilder()
            .replaceQueryParam("page", page + 1)
            .toUriString()

        val prevPageUrl = if (page > 1) {
            ServletUriComponentsBuilder.fromCurrentRequest()
                .replaceQueryParam("page", page - 1)
                .toUriString()
        } else {
            null
        }

        return CustomPageDTO(
            perPage = pageSize,
            currentPage = page,
            nextPageUrl = nextPageUrl,
            prevPageUrl = prevPageUrl,
            from = from,
            to = to,
            data = data,
        )
    }
}
