package com.github.wirye.anilibriakt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Torrent(
    val id: Long,
    val hash: String,
    val size: Long,
    val type: TorrentType? = null,
    val color: TorrentMediaColor? = null,
    val quality: TorrentMediaQuality? = null,
    val codec: TorrentMediaCodec? = null,
    val label: String? = null,
    val magnet: String? = null,
    @SerialName("filename") val fileName: String? = null,
    val bitrate: Long? = null,
    @SerialName("sort_order") val sortOrder: Long? = null,
    @SerialName("is_hardsub") val isHardsub: Boolean? = null,
    val seeders: Int? = null,
    val leechers: Int? = null,
    @SerialName("completed_times") val completedTimes: Int? = null,
    val description: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("torrent_members") val torrentMembers: List<TorrentMember>? = null,
    val release: TorrentRelease? = null,
)

@Serializable
data class TorrentEnumValue(
    val value: String? = null,
    val description: String? = null,
)

@Serializable
data class TorrentImage(
    val preview: String? = null,
    val thumbnail: String? = null,
    val optimized: TorrentImageOptimized? = null,
)

@Serializable
data class TorrentImageOptimized(
    val preview: String? = null,
    val thumbnail: String? = null,
)

@Serializable
data class TorrentMember(
    val id: String? = null,
    val role: TorrentEnumValue? = null,
    val nickname: String? = null,
    @SerialName("external_url") val externalUrl: String? = null,
    val user: TorrentMemberUser? = null,
)

@Serializable
data class TorrentMemberUser(
    val id: Long? = null,
    val avatar: TorrentImage? = null,
)

@Serializable
data class TorrentRelease(
    val id: Long? = null,
    val type: TorrentEnumValue? = null,
    val year: Int? = null,
    val name: TorrentReleaseName? = null,
    val alias: String? = null,
    val season: TorrentEnumValue? = null,
    val shikimori: TorrentExternalRating? = null,
    val mal: TorrentExternalRating? = null,
    val rating: TorrentReleaseRating? = null,
    val poster: TorrentImage? = null,
    @SerialName("fresh_at") val freshAt: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("is_ongoing") val isOngoing: Boolean? = null,
    @SerialName("age_rating") val ageRating: TorrentAgeRating? = null,
    @SerialName("publish_day") val publishDay: TorrentEnumValue? = null,
    val description: String? = null,
    val notification: String? = null,
    @SerialName("episodes_total") val episodesTotal: Int? = null,
    @SerialName("external_player") val externalPlayer: String? = null,
    @SerialName("is_in_production") val isInProduction: Boolean? = null,
    @SerialName("is_blocked_by_geo") val isBlockedByGeo: Boolean? = null,
    @SerialName("is_blocked_by_copyrights") val isBlockedByCopyrights: Boolean? = null,
    @SerialName("added_in_users_favorites") val addedInUsersFavorites: Int? = null,
    @SerialName("average_duration_of_episode") val averageDurationOfEpisode: Int? = null,
    @SerialName("added_in_planned_collection") val addedInPlannedCollection: Int? = null,
    @SerialName("added_in_watched_collection") val addedInWatchedCollection: Int? = null,
    @SerialName("added_in_watching_collection") val addedInWatchingCollection: Int? = null,
    @SerialName("added_in_postponed_collection") val addedInPostponedCollection: Int? = null,
    @SerialName("added_in_abandoned_collection") val addedInAbandonedCollection: Int? = null,
)

@Serializable
data class TorrentReleaseName(
    val main: String? = null,
    val english: String? = null,
    val alternative: String? = null,
)

@Serializable
data class TorrentExternalRating(
    val id: Long? = null,
    val url: String? = null,
    val votes: Int? = null,
    val rating: Double? = null,
)

@Serializable
data class TorrentReleaseRating(
    val average: Double? = null,
    val votes: Int? = null,
    /** Ключ: оценка (1..10), значение: количество голосов */
    val distribution: Map<String, Int>? = null,
)

@Serializable
data class TorrentAgeRating(
    val value: String? = null,
    val label: String? = null,
    @SerialName("is_adult") val isAdult: Boolean? = null,
    val description: String? = null,
)

@Serializable
data class TorrentType(
    val value: String? = null,
    val description: String? = null,
)

@Serializable
data class TorrentMediaColor(
    val value: String? = null,
    val description: String? = null,
)

@Serializable
data class TorrentMediaQuality(
    val value: String? = null,
    val description: String? = null,
)

@Serializable
data class TorrentMediaCodec(
    val value: String? = null,
    val description: String? = null,
    val label: String? = null,
    @SerialName("label_color") val labelColor: String? = null,
    @SerialName("label_is_visible") val labelIsVisible: Boolean? = null,
)
