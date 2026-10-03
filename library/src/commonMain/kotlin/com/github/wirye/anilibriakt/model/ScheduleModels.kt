package com.github.wirye.anilibriakt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val today: List<ScheduleRelease>,
    val tomorrow: List<ScheduleRelease>,
    val yesterday: List<ScheduleRelease>,
)

@Serializable
data class ScheduleRelease(
    val release: Title,
    @SerialName("full_season_is_released") val fullSeasonIsReleased: Boolean? = null,
    @SerialName("published_release_episode") val publishedReleaseEpisode: Episode? = null,
)