package com.github.wirye.anilibriakt.model

import com.github.wirye.anilibriakt.model.utils.toTitleGenres
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Title(
    val id: Long,
    val type: TitleType? = null,
    val year: Int? = null,
    val name: TitleName? = null,
    val alias: String? = null,
    val season: TitleSeason? = null,
    val poster: TitlePoster? = null,
    val description: String? = null,
    val genres: List<TitleGenre>? = null,
    val members: List<Member>? = null,
    val episodes: List<Episode>? = null,
    val torrents: List<Torrent>? = null,
    val sponsors: List<TitleSponsor>? = null,
    @SerialName("is_ongoing") val isOngoing: Boolean? = null,
    @SerialName("age_rating") val ageRating: AgeRating? = null,
    @SerialName("episodes_total") val episodesTotal: Int? = null,
    @SerialName("added_in_users_favorites") val addedInUsersFavorites: Long? = null,
    @SerialName("average_duration_of_episode") val averageDurationOfEpisode: Long? = null,
    @SerialName("added_in_planned_collection") val addedInPlannedCollection: Long? = null,
    @SerialName("added_in_watched_collection") val addedInWatchedCollection: Long? = null,
    @SerialName("added_in_watching_collection") val addedInWatchingCollection: Long? = null,
    @SerialName("added_in_postponed_collection") val addedInPostponedCollection: Long? = null,
    @SerialName("added_in_abandoned_collection") val addedInAbandonedCollection: Long? = null,
    @SerialName("is_in_production") val isInProduction: Boolean? = null,
    @SerialName("is_blocked_by_geo") val isBlockedByGeo: Boolean? = null,
    @SerialName("is_blocked_by_copyrights") val isBlockedByCopyrights: Boolean? = null,
    @SerialName("publish_day") val publishDay: PublishDay? = null,
    val shikimori: ExternalRating? = null,
    val mal: ExternalRating? = null
)

@Serializable
data class ListOfTitles(
    val data: List<Title>? = null
)

@Serializable
enum class Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

@Serializable
data class PublishDay(
    val value: String? = null,
    val description: String? = null
) {
    val getValue: Day?
        get() = when (value) {
            "Понедельник" -> Day.MONDAY
            "Вторник" -> Day.TUESDAY
            "Среда" -> Day.WEDNESDAY
            "Четверг" -> Day.THURSDAY
            "Пятница" -> Day.FRIDAY
            "Суббота" -> Day.SATURDAY
            "Воскресенье" -> Day.SUNDAY
            else -> null
        }
}

@Serializable
enum class TitleTypes {
    TV, ONA, WEB, OVA, OAD, MOVIE, DORAMA, SPECIAL
}

@Serializable
data class TitleType(
    val value: String? = null,
    val description: String? = null
) {
    val getValue: TitleTypes?
        get() = when (value) {
            "TV" -> TitleTypes.TV
            "ONA" -> TitleTypes.ONA
            "WEB" -> TitleTypes.WEB
            "OVA" -> TitleTypes.OVA
            "OAD" -> TitleTypes.OAD
            "MOVIE" -> TitleTypes.MOVIE
            "DORAMA" -> TitleTypes.DORAMA
            "SPECIAL" -> TitleTypes.SPECIAL
            else -> null
        }
}

@Serializable
data class TitleName(
    @SerialName("main") val russian: String? = null,
    val english: String? = null,
    val alternative: String? = null
)

@Serializable
enum class YearSezon {
    WINTER, SPRING, SUMMER, AUTUMN
}

@Serializable
data class TitleSeason(
    val value: String? = null
) {
    val getValue: YearSezon?
        get() = when (value) {
            null -> null
            "winter" -> YearSezon.WINTER
            "spring" -> YearSezon.SPRING
            "summer" -> YearSezon.SUMMER
            "fall", "autumn" -> YearSezon.AUTUMN
            else -> null
        }
}

@Serializable
enum class AgeRatings {
    R0_PLUS, R6_PLUS, R12_PLUS, R16_PLUS, R18_PLUS
}

@Serializable
data class AgeRating(
    val value: String? = null,
    val label: String? = null,
    @SerialName("is_adult") val isAdult: Boolean? = null,
) {
    val getValue: AgeRatings?
        get() = when (label) {
            "R0_PLUS" -> AgeRatings.R0_PLUS
            "R6_PLUS" -> AgeRatings.R6_PLUS
            "R12_PLUS" -> AgeRatings.R12_PLUS
            "R16_PLUS" -> AgeRatings.R16_PLUS
            "R18_PLUS" -> AgeRatings.R18_PLUS
            else -> null
        }
}

@Serializable
data class ExternalRating(
    val rating: Float? = null
)

@Serializable
data class TitlePoster(
    val preview: String? = null,
    val thumbnail: String? = null,
    val optimized: TitlePosterOptimized? = null
) {
    val fullPreviewUrl: String? get() = preview?.let { "https://anilibria.top$it" }
    val fullThumbnailUrl: String? get() = thumbnail?.let { "https://anilibria.top$it" }
}

@Serializable
data class TitlePosterOptimized(
    val preview: String? = null,
    val thumbnail: String? = null
) {
    val fullPreviewUrl: String? get() = preview?.let { "https://anilibria.top$it" }
    val fullThumbnailUrl: String? get() = thumbnail?.let { "https://anilibria.top$it" }
}

@Serializable
enum class TitleGenres {
    COMEDY, MECHA, PSYCHOLOGICAL, SHONEN, SEINEN, TRILLER, SCHOOL, DRAMA, MYSTERY, EVERYDAYLIFE, ROMANCE,
    SPORT, HORROR, ACTION, MARTIALARTS, DEMONS, GAMES, MAGIC, MUSIC, SHOUJO, SUPERPOWER, FANTASTIC, ETTY,
    VAMPIRES, DETECTIVE, HISTORICAL, ADVENTURES, MYSTICISM, FANTASY, CYBERPUNK, GIRLSLOVE, HAREM, JOSEI,
    ISEKAI
}

@Serializable
data class TitleGenre(
    val id: Long,
    val name: String? = null,
    val image: TitlePoster? = null,
    @SerialName("total_releases") val totalReleases: Long? = null,
) {
    val getValue: TitleGenres?
        get() = id.toTitleGenres()
}

@Serializable
data class TitleSponsor(
    val id: String,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    @SerialName("url_title") val urlTitle: String? = null,
)