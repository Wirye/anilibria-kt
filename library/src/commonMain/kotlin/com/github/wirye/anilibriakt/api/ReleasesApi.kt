package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.EpisodeUserWatchedWithTimecode
import com.github.wirye.anilibriakt.model.ListOfTitles
import com.github.wirye.anilibriakt.model.Member
import com.github.wirye.anilibriakt.model.Title
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

class ReleasesApi(
    private val tokenProvider: (suspend () -> String)? = null,
    private val httpClient: HttpClient
) {
    val episodes: EpisodesApi = EpisodesApi(tokenProvider = tokenProvider, httpClient = httpClient)
    val schedule: ScheduleApi = ScheduleApi(tokenProvider = tokenProvider, httpClient = httpClient)
    val genres: GenresApi = GenresApi(tokenProvider = tokenProvider, httpClient = httpClient)
    val franchises: Franchises = Franchises(tokenProvider = tokenProvider, httpClient = httpClient)
    val ratings: RatingsApi = RatingsApi(tokenProvider = tokenProvider, httpClient = httpClient)

    suspend fun recommended(limit: Int, releaseId: Long): Result<List<Title>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/recommended") {
            if (token.isNotBlank()) {
                bearerAuth(token)
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
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/latest") {
            if (token.isNotBlank()) {
                bearerAuth(token)
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
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/$id") {
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

    suspend fun getReleaseMembers(id: Long): Result<List<Member>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/$id/members") {
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

    suspend fun getReleaseEpisodesUserWatchedTimecodes(id: Long): Result<List<EpisodeUserWatchedWithTimecode>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/$id/episodes/timecodes") {
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

    suspend fun getRandomReleases(limit: Int): Result<List<Title>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/random") {
            if (token.isNotBlank()) {
                bearerAuth(token)
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
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/list") {
            if (token.isNotBlank()) {
                bearerAuth(token)
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