package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.ReleaseRating
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.Serializable

class RatingsApi(
    private val tokenProvider: (suspend () -> String)? = null,
    private val httpClient: HttpClient
) {
    suspend fun getMyReleaseRating(releaseId: Long): Result<ReleaseRating> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/$releaseId/rating") {
            if (token.isNotBlank()) {
                bearerAuth(token)
            }
        }

        when (result.status) {
            HttpStatusCode.OK -> {
                result.body()
            }
            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(result.status.value)
        }
    }

    @Serializable
    private data class setMyReleaseRatingRequest(
        val score: Long
    )

    suspend fun setMyReleaseRating(releaseId: Long, rating: Long): Result<Unit> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.post("https://anilibria.top/api/v1/anime/releases/$releaseId/rating") {
            contentType(ContentType.Application.Json)

            header(
                HttpHeaders.UserAgent,
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36"
            )

            if (token.isNotBlank()) {
                bearerAuth(token)
            }

            setBody(setMyReleaseRatingRequest(rating))
        }

        when (result.status) {
            HttpStatusCode.OK -> {}
            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(result.status.value)
        }
    }

    suspend fun deleteMyReleaseRating(releaseId: Long): Result<Unit> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.delete("https://anilibria.top/api/v1/anime/releases/$releaseId/rating") {
            if (token.isNotBlank()) {
                bearerAuth(token)
            }
        }

        when (result.status) {
            HttpStatusCode.OK -> {}
            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(result.status.value)
        }
    }
}