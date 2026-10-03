package com.github.wirye.anilibriakt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Franchise(
    val id: String,
    @SerialName("name") val russianName: String? = null,
    val image: TitlePoster? = null,
    val rating: Float? = null,
    @SerialName("last_year") val lastYear: Int? = null,
    @SerialName("first_year") val firstYear: Int? = null,
    @SerialName("name_english") val englishName: String? = null,
    @SerialName("total_episodes") val totalEpisodes: Int? = null,
    @SerialName("total_releases") val totalReleases: Int? = null,
    @SerialName("total_duration") val totalDuration: String? = null,
    @SerialName("total_duration_in_seconds") val totalDurationInSeconds: Long? = null,
    @SerialName("franchise_releases") val franchiseReleases: List<FranchiseRelease>? = null
)

@Serializable
data class FranchiseRelease(
    val id: String,
    @SerialName("sort_order") val sortOrder: Int? = null,
    @SerialName("release_id") val releaseId: Long,
    @SerialName("franchise_id") val franchiseId: String,
    val release: Title,
)