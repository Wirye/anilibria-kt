package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.ListWithPagination
import com.github.wirye.anilibriakt.model.Torrent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode

class TorrentsApi(
    private val passkeyProvider: (suspend () -> String)? = null,
    private val tokenProvider: (suspend () -> String)? = null,
    private val httpClient: HttpClient
) {
    suspend fun getTorrents(
        page: Int? = null,
        limit: Int? = null,
    ): Result<ListWithPagination<Torrent>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result =
            httpClient.get("https://anilibria.top/api/v1/anime/torrents") {
                if (token.isNotBlank()) {
                    bearerAuth(token)
                }
                page?.let { parameter("page", it) }
                limit?.let { parameter("limit", it) }
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

    suspend fun getTorrent(idOrHash: String): Result<Torrent> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/torrents/$idOrHash") {
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

    suspend fun getTorrentsForRelease(releaseId: Long): Result<List<Torrent>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/torrents/release/$releaseId") {
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

    suspend fun getTorrentRss(limit: Int? = null): Result<String> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""
        val passkey = if (passkeyProvider != null) passkeyProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/torrents/rss") {
            if (token.isNotBlank()) {
                bearerAuth(token)
            }
            if (passkey.isNotBlank()) {
                parameter("passkey", passkey)
            }
            limit?.let { parameter("limit", it) }
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

    suspend fun getTorrentRssForRelease(releaseId: Long): Result<String> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""
        val passkey = if (passkeyProvider != null) passkeyProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/torrents/rss/release/$releaseId") {
            if (token.isNotBlank()) {
                bearerAuth(token)
            }
            if (passkey.isNotBlank()) {
                parameter("passkey", passkey)
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
}