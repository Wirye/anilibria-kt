package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.EpisodeTimecode
import com.github.wirye.anilibriakt.model.EpisodeUserWatchedWithTimecode
import com.github.wirye.anilibriakt.model.ListWithPagination
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant

class HistoryApi(
    private val httpClient: HttpClient,
    private val tokenProvider: (suspend () -> String)? = null
) {
    suspend fun getHistory(page: Int? = null, limit: Int? = null): Result<ListWithPagination<EpisodeUserWatchedWithTimecode>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/accounts/users/me/views/history") {
            if (token.isNotEmpty()) {
                bearerAuth(token)
            }

            url {
                if (page != null) {
                    parameters.append("page", page.toString())
                }

                if (limit != null) {
                    parameters.append("limit", limit.toString())
                }
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

    suspend fun getTimecodes(since: Instant? = null): Result<List<EpisodeTimecode>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result =
            httpClient.get("https://anilibria.top/api/v1/accounts/users/me/views/timecodes") {
                if (token.isNotBlank()) {
                    bearerAuth(token)
                }
                since?.let { parameter("since", it.toString()) }
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
    private data class EpisodeTimecodeRequest(
        @SerialName("release_episode_id") val episodeId: String,
        @SerialName("time") val timecode: Float,
        @SerialName("is_watched") val isWatched: Boolean,
    )

    suspend fun updateTimecodes(timecodes: List<EpisodeTimecode>): Result<Unit> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val timecodes = timecodes.map { EpisodeTimecodeRequest(it.episodeId, it.timecode, it.isWatched) }

        val result = httpClient.post("https://anilibria.top/api/v1/accounts/users/me/views/timecodes") {
            header(
                HttpHeaders.UserAgent,
                "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Mobile Safari/537.36"
            )
            contentType(ContentType.Application.Json)

            if (token.isNotBlank()) {
                bearerAuth(token)
            }

            setBody(timecodes)
        }

        when (result.status) {
            HttpStatusCode.OK -> {}

            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(result.status.value)
        }
    }

    @Serializable
    private data class DeleteTimecodesRequest(
        @SerialName("release_episode_id") val episodeId: String,
    )

    suspend fun deleteTimecodes(timecodes: List<String>): Result<Unit> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val timecodes = timecodes.map { DeleteTimecodesRequest(it) }

        val result = httpClient.delete("https://anilibria.top/api/v1/accounts/users/me/views/timecodes") {
            header(
                HttpHeaders.UserAgent,
                "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Mobile Safari/537.36"
            )

            contentType(ContentType.Application.Json)

            if (token.isNotBlank()) {
                bearerAuth(token)
            }

            setBody(timecodes)
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