package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.EpisodeUserWatchedTimecode
import com.github.wirye.anilibriakt.model.ListOfTitles
import com.github.wirye.anilibriakt.model.Member
import com.github.wirye.anilibriakt.model.Title
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

class ReleasesApi(
    private val tokenProvider: () -> String,
    private val httpClient: HttpClient
) {
    val episodes: EpisodesApi = EpisodesApi(tokenProvider = tokenProvider, httpClient = httpClient)

    suspend fun recommended(limit: Int, releaseId: Long): Result<List<Title>> = runCatching {
        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/recommended") {
            val token = tokenProvider()
            if (token.isNotBlank()) {
                bearerAuth(tokenProvider())
            }

            url.parameters.append("limit", limit.toString())
            url.parameters.append("release_id", releaseId.toString())
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

    suspend fun latest(limit: Int): Result<List<Title>> = runCatching {
        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/latest") {
            val token = tokenProvider()
            if (token.isNotBlank()) {
                bearerAuth(tokenProvider())
            }

            url.parameters.append("limit", limit.toString())
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

    suspend fun getRelease(id: Long): Result<Title> = runCatching {
        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/$id") {
            val token = tokenProvider()
            if (token.isNotBlank()) {
                bearerAuth(tokenProvider())
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

    suspend fun getReleaseMembers(id: Long): Result<List<Member>> = runCatching {
        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/$id/members") {
            val token = tokenProvider()
            if (token.isNotBlank()) {
                bearerAuth(tokenProvider())
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

    suspend fun getReleaseEpisodesUserWatchedTimecodes(id: Long): Result<List<EpisodeUserWatchedTimecode>> = runCatching {
        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/$id/episodes/timecodes") {
            val token = tokenProvider()
            if (token.isNotBlank()) {
                bearerAuth(tokenProvider())
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

    suspend fun getRandomReleases(limit: Int): Result<List<Title>> = runCatching {
        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/random") {
            val token = tokenProvider()
            if (token.isNotBlank()) {
                bearerAuth(tokenProvider())
            }

            url.parameters.append("limit", limit.toString())
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

    suspend fun getManyReleases(ids: List<Long>): Result<ListOfTitles> = runCatching {
        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/list") {
            val token = tokenProvider()
            if (token.isNotBlank()) {
                bearerAuth(tokenProvider())
            }

            url.parameters.append("ids", ids.joinToString(","))
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