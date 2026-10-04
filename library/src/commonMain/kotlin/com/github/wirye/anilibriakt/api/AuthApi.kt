package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.AuthResponse
import com.github.wirye.anilibriakt.model.LoginRequest
import com.github.wirye.anilibriakt.model.UserProfile
import com.github.wirye.anilibriakt.model.UserProfileFields
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class AuthApi internal constructor(
    private val httpClient: HttpClient,
    private val tokenProvider: (suspend () -> String)? = null
) {
    /**
     * Авторизация
     */
    suspend fun login(login: String, password: String): Result<String> = runCatching {
        val response = httpClient.post("https://anilibria.top/api/v1/accounts/users/auth/login") {
            contentType(ContentType.Application.Json)

            setBody(LoginRequest(login = login, password = password))
        }

        val responseText = response.bodyAsText()

        if (responseText.trim().startsWith("<")) {
            throw AniLibriaException.HtmlResponseException(response.status.value)
        }

        when (response.status) {
            HttpStatusCode.OK -> {
                val authBody = response.body<AuthResponse>()
                authBody.token
            }

            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(response.status.value)
        }
    }

    suspend fun logout(): Result<Unit> = runCatching {
        val token = if (tokenProvider != null) {
            tokenProvider()
        } else ""

        val response = httpClient.post("https://anilibria.top/api/v1/accounts/users/auth/logout") {
            if (token.isNotEmpty()) {
                bearerAuth(token)
            }
        }

        when (response.status) {
            HttpStatusCode.OK -> {}

            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(response.status.value)
        }
    }

    /**
     * Получение профиля
     */
    suspend fun getProfile(
        requestedData: List<UserProfileFields>
    ): Result<UserProfile> = runCatching {
        val token = if (tokenProvider != null) {
            tokenProvider()
        } else ""

        val response = httpClient.get("https://anilibria.top/api/v1/accounts/users/me/profile") {
            if (token.isNotEmpty()) {
                bearerAuth(token)
            }


            if (requestedData.isNotEmpty()) {
                url.parameters.append(
                    "include",
                    requestedData.joinToString(",") { it.name.lowercase() })
            }
        }

        when (response.status) {
            HttpStatusCode.OK -> Unit
            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(response.status.value)
        }

        response.body()
    }
}
