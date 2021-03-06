package com.example.autosup.apiService

import com.example.autosup.httpService.HtmlPageService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject

class ApiClientImpl @Inject constructor() : ApiClient  {

    private val AUTOSUP_BASE_URL = "https://autosup.by/"

     override suspend fun getAutosupMainHtmlPage(): Deferred<Response<String>> {
         val htmlPageService = initializeRetrofit()
         return htmlPageService.getAutosupMainPage()
    }

    override suspend fun getCarBrandHtmlPage(url: String): Deferred<Response<String>> {
        val htmlPageService = initializeRetrofit()
        return htmlPageService.getAutosupCarPage(url)
    }

    override suspend fun getPartsPage(url: String): Deferred<Response<String>> {
        val htmlPageService = initializeRetrofit()
        return htmlPageService.getAllParts(url)
    }

    override suspend fun getAllCarBrands(url: String): Deferred<Response<String>> {
        val htmlPageService = initializeRetrofit()
        return htmlPageService.getAllCarParts(url)
    }

    override suspend fun getEnginesHtmlPage(url: String): Deferred<Response<String>> {
        val htmlPageService = initializeRetrofit()
        return htmlPageService.getAutosupEnginePage(url)
    }

    private fun initializeRetrofit(): HtmlPageService {
        val retrofit: Retrofit = initializeMainAutosupPage()
        return retrofit.create(HtmlPageService::class.java)
    }

    private fun initializeMainAutosupPage(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AUTOSUP_BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }
}