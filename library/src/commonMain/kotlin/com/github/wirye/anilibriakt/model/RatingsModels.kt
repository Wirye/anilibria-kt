package com.github.wirye.anilibriakt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseRating(
    @SerialName("release_id") val releaseId: Long,
    val score: Long? = null
)