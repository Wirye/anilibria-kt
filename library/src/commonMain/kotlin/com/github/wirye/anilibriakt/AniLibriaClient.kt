package com.github.wirye.anilibriakt

import com.github.wirye.anilibriakt.api.AccountApi
import com.github.wirye.anilibriakt.api.AdsApi
import com.github.wirye.anilibriakt.api.ReleasesApi
import com.github.wirye.anilibriakt.api.SearchApi
import com.github.wirye.anilibriakt.api.TorrentsApi
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * @param userAgent a string in the format "MyApp/1.0 ( me@example.com )": app name, version, and contact.
 * Not required for AniLibria. If you provide your own [customHttpClient], you do not need to set the User-Agent in it:
 * the library will add this one.
 */
class AniLibriaClient(
    userAgent: String,
    customHttpClient: HttpClient? = null,
    tokenProvider: (suspend () -> String)? = null,
    private val passkeyProvider: (suspend () -> String)? = null
) {
    private val httpClient: HttpClient = run {
        val setup: HttpClientConfig<*>.() -> Unit = {
            install(UserAgent) { agent = userAgent }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
        customHttpClient?.config(setup) ?: HttpClient(setup)
    }

    /** Модуль работы с аккаунтом */
    val account: AccountApi = AccountApi(httpClient = httpClient, tokenProvider = tokenProvider)

    /** Модуль работы с поиском */
    val search: SearchApi = SearchApi(httpClient = httpClient, tokenProvider = tokenProvider)

    /** Модуль работы с релизами */
    val releases: ReleasesApi = ReleasesApi(httpClient = httpClient, tokenProvider = tokenProvider)

    /** Модуль работы с рекламой */
    val ads: AdsApi = AdsApi(httpClient = httpClient, tokenProvider = tokenProvider)
    /** Модуль работы с торрентами */
    val torrents: TorrentsApi = TorrentsApi(httpClient = httpClient, tokenProvider = tokenProvider, passkeyProvider = passkeyProvider)
}