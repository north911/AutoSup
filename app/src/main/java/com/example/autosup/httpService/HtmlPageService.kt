package com.example.autosup.httpService

import android.provider.Contacts
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface HtmlPageService {
    @GET("/")
    fun getAutosupMainPage():  Deferred<Response<String>>
}