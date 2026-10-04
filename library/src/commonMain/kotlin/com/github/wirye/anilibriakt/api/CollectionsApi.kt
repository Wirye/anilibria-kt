package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.AgeRatings
import com.github.wirye.anilibriakt.model.Collection
import com.github.wirye.anilibriakt.model.Collections
import com.github.wirye.anilibriakt.model.ListWithPagination
import com.github.wirye.anilibriakt.model.Title
import com.github.wirye.anilibriakt.model.TitleGenres
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

class CollectionsApi(
    private val tokenProvider: (suspend () -> String)? = null,
    private val httpClient: HttpClient
) {
    suspend fun getReleasesIds(): Result<List<Collection>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result =
            httpClient.get("https://anilibria.top/api/v1/accounts/users/me/collections/ids") {
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

    suspend fun getCollectionReleases(
        page: Int? = null,
        limit: Int? = null,
        search: String? = null,
        typeOfCollection: Collections,
        ageRatings: List<AgeRatings>? = null,
        types: List<TitleTypes>? = null,
        genres: List<TitleGenres>? = null,
        years: List<Long>? = null
    ): Result<ListWithPagination<Title>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result =
            httpClient.get("https://anilibria.top/api/v1/accounts/users/me/collections/releases") {
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

                    typeOfCollection.let {
                        parameters.append("type_of_collection", it.name)
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
    data class AddCollectionRequest(
        @SerialName("release_id") val releaseId: Long,
        @SerialName("type_of_collection") val typeOfCollection: Collections
    )

    @Serializable
    private data class PrivateAddCollectionRequest(
        @SerialName("release_id") val releaseId: Long,
        @SerialName("type_of_collection") val typeOfCollection: String
    )

    suspend fun addReleaseToCollection(objs: List<AddCollectionRequest>): Result<Unit> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.post("https://anilibria.top/api/v1/accounts/users/me/collections") {

            contentType(ContentType.Application.Json)

            if (token.isNotBlank()) {
                bearerAuth(token)
            }

            setBody(
                objs.map {
                    PrivateAddCollectionRequest(
                        releaseId = it.releaseId,
                        typeOfCollection = it.typeOfCollection.name
                    )
                }
            )
        }

        when (result.status) {
            HttpStatusCode.OK -> {}

            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(result.status.value)
        }
    }

    suspend fun deleteReleaseToCollection(objs: List<AddCollectionRequest>): Result<Unit> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.delete("https://anilibria.top/api/v1/accounts/users/me/collections") {

            contentType(ContentType.Application.Json)

            if (token.isNotBlank()) {
                bearerAuth(token)
            }

            setBody(
                objs.map {
                    PrivateAddCollectionRequest(
                        releaseId = it.releaseId,
                        typeOfCollection = it.typeOfCollection.name
                    )
                }
            )
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