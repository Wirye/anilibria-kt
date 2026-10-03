package com.github.wirye.anilibriakt.api

import com.github.wirye.anilibriakt.exception.AniLibriaException
import com.github.wirye.anilibriakt.model.VastAd
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

class AdsApi(
    private val httpClient: HttpClient,
    private val tokenProvider: (suspend () -> String)? = null
) {
    val manifestUrl = "https://anilibria.top/api/v1/media/manifest.xml"

    suspend fun getAvailableVastAds(): Result<List<VastAd>> = runCatching {
        val token = if (tokenProvider != null) tokenProvider() else ""

        val result = httpClient.get("https://anilibria.top/api/v1/media/vasts/") {
            if (token.isNotEmpty()) {
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