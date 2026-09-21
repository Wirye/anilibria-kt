package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.Episode
import com.github.wirye.anilibriakt.model.EpisodeUserWatchedTimecode
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

class EpisodesApi(
    private val tokenProvider: () -> String,
    private val httpClient: HttpClient
) {
    suspend fun getEpisodeUserWatchedTimecodes(episodeId: String): Result<EpisodeUserWatchedTimecode> = runCatching {
        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/episodes/$episodeId/timecode") {
            val token = tokenProvider()
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

    suspend fun getEpisode(episodeId: String): Result<Episode> = runCatching {
        val result = httpClient.get("https://anilibria.top/api/v1/anime/releases/episodes/$episodeId") {
            val token = tokenProvider()
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
}