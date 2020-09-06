package com.example.autosup.apiService

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response

interface ApiClient {
     suspend fun getAutosupMainHtmlPageAsString(): Deferred<Response<String>>
}