package com.github.wirye.anilibriakt.api

import io.ktor.client.HttpClient

class AccountApi(
    private val tokenProvider: (suspend () -> String)? = null,
    private val httpClient: HttpClient
) {
    val auth: AuthApi = AuthApi(tokenProvider = tokenProvider, httpClient = httpClient)
    val favorites: FavoritesApi = FavoritesApi(tokenProvider = tokenProvider, httpClient = httpClient)
    val history: HistoryApi = HistoryApi(tokenProvider = tokenProvider, httpClient = httpClient)
    val collections: CollectionsApi = CollectionsApi(tokenProvider = tokenProvider, httpClient = httpClient)
}
