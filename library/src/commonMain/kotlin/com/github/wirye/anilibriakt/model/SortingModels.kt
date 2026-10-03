package com.github.wirye.anilibriakt.model

import kotlinx.serialization.Serializable

@Serializable
enum class Sort {
    CREATED_AT_DESC, CREATED_AT_ASC, FRESH_AT_DESC, FRESH_AT_ASC, RATING_DESC, RATING_ASC, YEAR_DESC, YEAR_ASC
}

@Serializable
data class Sorting(
    val value: String? = null,
    val label: String? = null,
    val description: String? = null
) {
    val getValue: Sort?
        get() = when (label) {
            "CREATED_AT_DESC" -> Sort.CREATED_AT_DESC
            "CREATED_AT_ASC" -> Sort.CREATED_AT_ASC
            "FRESH_AT_DESC" -> Sort.FRESH_AT_DESC
            "FRESH_AT_ASC" -> Sort.FRESH_AT_ASC
            "RATING_DESC" -> Sort.RATING_DESC
            "RATING_ASC" -> Sort.RATING_ASC
            "YEAR_DESC" -> Sort.YEAR_DESC
            "YEAR_ASC" -> Sort.YEAR_ASC
            else -> null
        }
}