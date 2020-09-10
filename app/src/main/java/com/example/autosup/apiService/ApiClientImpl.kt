package com.example.autosup.apiService

import com.example.autosup.httpService.HtmlPageService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiClientImpl : ApiClient {

    private val AUTOSUP_BASE_URL = "https://autosup.by/"

     override suspend fun getAutosupMainHtmlPageAsString(): Deferred<Response<String>> {
        val retrofit: Retrofit = initializeMainAutosupPage()
        val htmlPageService = retrofit.create(HtmlPageService::class.java)
        return htmlPageService.getAutosupMainPage()
    }

    private fun initializeMainAutosupPage(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AUTOSUP_BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }
}