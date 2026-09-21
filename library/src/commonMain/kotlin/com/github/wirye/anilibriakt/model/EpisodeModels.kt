package com.github.wirye.anilibriakt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    @SerialName("id") val episodeId: String,
    @SerialName("release_id") val releaseId: Long,
    @SerialName("name") val russianName: String? = null,
    @SerialName("name_english") val englishName: String? = null,
    val ordinal: Long? = null,
    val opening: Opening? = null,
    val ending: Ending? = null,
    val duration: Long? = null,
    val preview: TitlePoster? = null,
    @SerialName("hls_480") val hls480: String? = null,
    @SerialName("hls_720") val hls720: String? = null,
    @SerialName("hls_1080") val hls1080: String? = null,
)

@Serializable
data class Opening(
    val start: Long? = null,
    val end: Long? = null
)

@Serializable
data class Ending(
    val start: Long? = null,
    val end: Long? = null
)

@Serializable
data class EpisodeUserWatchedTimecode(
    @SerialName("id") val releaseId: Long,
    val time: Float,
    @SerialName("user_id") val userId: Long,
    @SerialName("is_watched") val isWatched: Boolean,
    @SerialName("release_episode_id") val episodeId: String,
)