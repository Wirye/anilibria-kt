package com.github.wirye.anilibriakt.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.add
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.float
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive

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
    val release: Title? = null
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
data class EpisodeUserWatchedWithTimecode(
    @SerialName("id") val releaseId: Long,
    val time: Float,
    @SerialName("user_id") val userId: Long,
    @SerialName("is_watched") val isWatched: Boolean,
    @SerialName("release_episode_id") val episodeId: String,
    @SerialName("release_episode") val episode: Episode? = null
)

@Serializable(with = EpisodeTimecodeSerializer::class)
data class EpisodeTimecode(
    val episodeId: String,
    val timecode: Float,
    val isWatched: Boolean,
)

internal object EpisodeTimecodeSerializer : KSerializer<EpisodeTimecode> {

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("EpisodeTimecode")

    override fun deserialize(decoder: Decoder): EpisodeTimecode {
        val json = decoder as? JsonDecoder
            ?: error("EpisodeTimecode можно десериализовать только из JSON")
        val array = json.decodeJsonElement().jsonArray

        return EpisodeTimecode(
            episodeId = array[0].jsonPrimitive.content,
            timecode = array[1].jsonPrimitive.float,
            isWatched = array[2].jsonPrimitive.boolean,
        )
    }

    override fun serialize(encoder: Encoder, value: EpisodeTimecode) {
        val json = encoder as? JsonEncoder
            ?: error("EpisodeTimecode можно сериализовать только в JSON")
        json.encodeJsonElement(
            buildJsonArray {
                add(value.episodeId)
                add(value.timecode)
                add(value.isWatched)
            }
        )
    }
}