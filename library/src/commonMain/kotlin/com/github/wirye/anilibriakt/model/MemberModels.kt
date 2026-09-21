package com.github.wirye.anilibriakt.model

import kotlinx.serialization.Serializable

@Serializable
data class Member(
    val id: String,
    val role: MemberRole? = null,
    val user: MemberUser? = null,
    val nickname: String? = null
)

@Serializable
data class MemberRole(
    val value: String? = null,
    val description: String? = null
)

@Serializable
data class MemberUser(
    val id: Long,
    val avatar: TitlePoster? = null,
)