package com.github.wirye.anilibriakt

import com.github.wirye.anilibriakt.api.AccountApi
import com.github.wirye.anilibriakt.api.AdsApi
import com.github.wirye.anilibriakt.api.ReleasesApi
import com.github.wirye.anilibriakt.api.SearchApi
import com.github.wirye.anilibriakt.api.TorrentsApi
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class AniLibriaClient(
    customHttpClient: HttpClient? = null,
    tokenProvider: (suspend () -> String)? = null,
    private val passkeyProvider: (suspend () -> String)? = null
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
    val account: AccountApi = AccountApi(httpClient = httpClient, tokenProvider = tokenProvider)

    /** Модуль работы с поиском */
    val search: SearchApi = SearchApi(httpClient = httpClient, tokenProvider = tokenProvider)

    /** Модуль работы с релизами */
    val releases: ReleasesApi = ReleasesApi(httpClient = httpClient, tokenProvider = tokenProvider)

    /** Модуль работы с рекламой */
    val ads: AdsApi = AdsApi(httpClient = httpClient, tokenProvider = tokenProvider)
    val torrents: TorrentsApi = TorrentsApi(httpClient = httpClient, tokenProvider = tokenProvider, passkeyProvider = passkeyProvider)
}