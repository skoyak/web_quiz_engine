package app.infra.rest.dto

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

data class PaginatedResponse<T>(
    val totalPages: Int,
    val totalElements: Int,
    val last: Boolean,
    val first: Boolean,
    val sort: Sort,
    val number: Int,
    val numberOfElements: Int,
    val size: Int,
    val empty: Boolean,
    val pageable:  Pageable,
    val content: List<T?>,
)