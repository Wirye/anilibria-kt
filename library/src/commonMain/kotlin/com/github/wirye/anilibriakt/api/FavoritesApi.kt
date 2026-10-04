package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.AgeRating
import com.github.wirye.anilibriakt.model.AgeRatings
import com.github.wirye.anilibriakt.model.ListWithPagination
import com.github.wirye.anilibriakt.model.Sort
import com.github.wirye.anilibriakt.model.Sorting
import com.github.wirye.anilibriakt.model.Title
import com.github.wirye.anilibriakt.model.TitleGenre
import com.github.wirye.anilibriakt.model.TitleGenres
import com.github.wirye.anilibriakt.model.TitleType
import com.github.wirye.anilibriakt.model.TitleTypes
import com.github.wirye.anilibriakt.model.utils.toLong
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class FavoritesApi(
    private val tokenProvider: (suspend () -> String)? = null,
    private val httpClient: HttpClient
) {
    suspend fun getIdsOfFavoriteReleases(): Result<List<Long>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result =
            httpClient.get("https://anilibria.top/api/v1/accounts/users/me/favorites/ids") {
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

    suspend fun getFavoriteReleases(
        page: Int? = null,
        limit: Int? = null,
        search: String? = null,
        sort: Sort? = null,
        ageRatings: List<AgeRatings>? = null,
        types: List<TitleTypes>? = null,
        genres: List<TitleGenres>? = null,
        years: List<Long>? = null
    ): Result<ListWithPagination<Title>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result =
            httpClient.get("https://anilibria.top/api/v1/accounts/users/me/favorites/releases") {
                if (token.isNotBlank()) {
                    bearerAuth(token)
                }

                url {
                    if (page != null) {
                        parameters.append("page", page.toString())
                    }

                    if (limit != null) {
                        parameters.append("limit", limit.toString())
                    }

                    search?.takeIf { it.isNotBlank() }?.let {
                        parameters.append("f[search]", it)
                    }

                    sort?.let {
                        parameters.append("f[sorting]", it.name)
                    }

                    ageRatings?.forEach { rating ->
                        parameters.append("f[age_ratings]", rating.name)
                    }

                    types?.forEach { type ->
                        parameters.append("f[types]", type.name)
                    }

                    genres?.map { it.toLong() }?.takeIf { it.isNotEmpty() }?.let { genreIds ->
                        parameters.append("f[genres]", genreIds.joinToString(","))
                    }

                    years?.takeIf { it.isNotEmpty() }?.let { yearList ->
                        parameters.append("f[years]", yearList.joinToString(","))
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

    @Serializable
    data class AddFavoriteRequest(
        @SerialName("release_id") val releaseId: Long
    )

    suspend fun addReleaseToFavorites(releaseId: Long): Result<Unit> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.post("https://anilibria.top/api/v1/accounts/users/me/favorites") {
            contentType(ContentType.Application.Json)

            if (token.isNotBlank()) {
                bearerAuth(token)
            }

            setBody(listOf(AddFavoriteRequest(releaseId)))
        }

        when (result.status) {
            HttpStatusCode.OK -> {}

            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(result.status.value)
        }
    }

    suspend fun removeReleaseFromFavorites(releaseId: Long): Result<Unit> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.delete("https://anilibria.top/api/v1/accounts/users/me/favorites") {
            contentType(ContentType.Application.Json)

            if (token.isNotBlank()) {
                bearerAuth(token)
            }

            setBody(listOf(AddFavoriteRequest(releaseId)))
        }

        when (result.status) {
            HttpStatusCode.OK -> {}

            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(result.status.value)
        }
    }

    suspend fun getAvailableUserAgeRatings(): Result<List<AgeRating>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result =
            httpClient.get("https://anilibria.top/api/v1/accounts/users/me/favorites/references/age-ratings") {
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

    suspend fun getAvailableUserGenres(): Result<List<TitleGenre>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result =
            httpClient.get("https://anilibria.top/api/v1/accounts/users/me/favorites/references/genres") {
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

    suspend fun getAvailableUserSoring(): Result<List<Sorting>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result =
            httpClient.get("https://anilibria.top/api/v1/accounts/users/me/favorites/references/sorting") {
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

    suspend fun getAvailableUserTypes(): Result<List<TitleType>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result =
            httpClient.get("https://anilibria.top/api/v1/accounts/users/me/favorites/references/types") {
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

    suspend fun getAvailableUserYears(): Result<List<Long>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result =
            httpClient.get("https://anilibria.top/api/v1/accounts/users/me/favorites/references/years") {
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
