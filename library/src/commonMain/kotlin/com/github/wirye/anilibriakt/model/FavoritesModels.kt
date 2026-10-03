package com.github.wirye.anilibriakt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListWithPagination<T> (
    @SerialName("data") val data: List<T>,
    val meta: PaginationMeta? = null
)

@Serializable
data class PaginationMeta(
    val pagination: Pagination
)

@Serializable
data class Pagination(
    val total: Long? = null,
    val count: Long? = null,
    @SerialName("per_page") val perPage: Long? = null,
    @SerialName("current_page") val currentPage: Long? = null,
    @SerialName("total_pages") val totalPages: Long? = null
)