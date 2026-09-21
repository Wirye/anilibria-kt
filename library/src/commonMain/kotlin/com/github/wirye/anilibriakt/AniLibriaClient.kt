package com.github.wirye.anilibriakt

import com.github.wirye.anilibriakt.api.AuthApi
import com.github.wirye.anilibriakt.api.ReleasesApi
import com.github.wirye.anilibriakt.api.SearchApi
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class AniLibriaClient(
    customHttpClient: HttpClient? = null,
    tokenProvider: () -> String
) {
    private val httpClient: HttpClient = customHttpClient ?: HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    /** Модуль работы с аккаунтом */
    val auth: AuthApi = AuthApi(httpClient = httpClient, tokenProvider = tokenProvider)

    /** Модуль работы с поиском */
    val search: SearchApi = SearchApi(httpClient = httpClient, tokenProvider = tokenProvider)

    /** Модуль работы с релизами */
    val releases: ReleasesApi = ReleasesApi(httpClient = httpClient, tokenProvider = tokenProvider)
}