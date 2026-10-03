package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.SearchOffer
import com.github.wirye.anilibriakt.model.Title
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

class SearchApi(
    private val httpClient: HttpClient,
    private val tokenProvider: (suspend () -> String)? = null
) {
    suspend fun search(query: String, limit: Int): Result<List<Title>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val response = httpClient.get("https://anilibria.top/api/v1/app/search/releases") {

            if (token.isNotBlank()) {
                bearerAuth(token)
            }
            url.parameters.append("query", query)
        }

        when (response.status) {
            HttpStatusCode.OK -> {
                response.body<List<Title>>().take(limit)
            }
            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(response.status.value)
        }
    }

    /**
    * @return Результат поиска в виде списка названий
    * */
    suspend fun getOffers(query: String, limit: Int): Result<List<SearchOffer>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val response = httpClient.get("https://anilibria.top/api/v1/app/search/releases") {
            if (token.isNotBlank()) {
                bearerAuth(token)
            }
            url.parameters.append("query", query)
            url.parameters.append("include", "name")
        }

        when (response.status) {
            HttpStatusCode.OK -> {
                response.body<List<SearchOffer>>().take(limit)
            }
            HttpStatusCode.Forbidden -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.Unauthorized -> throw AniLibriaException.InvalidCredentialsException()
            HttpStatusCode.UnprocessableEntity -> throw AniLibriaException.ValidationErrorException()
            else -> throw AniLibriaException.ServerErrorException(response.status.value)
        }
    }
}
