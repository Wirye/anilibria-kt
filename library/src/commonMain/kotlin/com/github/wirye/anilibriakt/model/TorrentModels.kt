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
    val fileName: String? = null,
    val bitrate: Long? = null,
    @SerialName("sort_order") val sortOrder: Long? = null,
    @SerialName("is_hardsub") val isHardsub: Boolean? = null,
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
