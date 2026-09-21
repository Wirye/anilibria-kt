package com.github.wirye.anilibriakt.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchOffer(
    val name: TitleName,
)