package com.example.autosup.httpService

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface HtmlPageService {
    @GET("/")
    fun getAutosupMainPage(): Deferred<Response<String>>

    @GET
    fun getAutosupCarPage(@Url url: String): Deferred<Response<String>>

    @GET
    fun getAutosupEnginePage(@Url url: String): Deferred<Response<String>>

    @GET
    fun getAllCarParts(@Url url: String): Deferred<Response<String>>
}