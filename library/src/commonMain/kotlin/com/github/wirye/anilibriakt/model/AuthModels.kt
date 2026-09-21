package com.github.wirye.anilibriakt.model

import kotlinx.serialization.Serializable

@Serializable
internal data class LoginRequest(
    val login: String,
    val password: String
)

@Serializable
data class AuthResponse(
    val token: String
)

@Serializable
enum class UserProfileFields {
    Id, NickName, Login, Email, Avatar
}

@Serializable
data class UserProfile(
    val id: Long? = null,
    val nickname: String? = null,
    val login: String? = null,
    val email: String? = null,
    val avatar: UserAvatar? = null // <-- Меняем String? на UserAvatar?
)

@Serializable
data class UserAvatar(
    val preview: String? = null,
    val thumbnail: String? = null,
    val optimized: UserAvatarOptimized? = null
) {
    val fullPreviewUrl: String?
        get() = preview?.let { "https://anilibria.top$it" }

    val fullThumbnailUrl: String?
        get() = thumbnail?.let { "https://anilibria.top$it" }
}

@Serializable
data class UserAvatarOptimized(
    val preview: String? = null,
    val thumbnail: String? = null
) {
    val fullPreviewUrl: String?
        get() = preview?.let { "https://anilibria.top$it" }

    val fullThumbnailUrl: String?
        get() = thumbnail?.let { "https://anilibria.top$it" }
}
