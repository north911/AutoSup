package com.example.autosup.apiService

import kotlinx.coroutines.Deferred
import retrofit2.Response

interface ApiClient {
    suspend fun getAutosupMainHtmlPage(): Deferred<Response<String>>
    suspend fun getCarBrandHtmlPage(url: String): Deferred<Response<String>>
}