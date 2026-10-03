package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.Franchise
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

class Franchises(
    private val httpClient: HttpClient,
    private val tokenProvider: (suspend () -> String)? = null
) {
    suspend fun getFranchises(): Result<List<Franchise>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/franchises") {
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

    suspend fun getFranchise(id: String): Result<Franchise> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/franchises/$id") {
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

    suspend fun getRandomFranchises(limit: Int): Result<List<Franchise>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/franchises/random") {
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

    suspend fun getFranchisesForRelease(releaseId: Long): Result<List<Franchise>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/anime/franchises/release/$releaseId") {
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