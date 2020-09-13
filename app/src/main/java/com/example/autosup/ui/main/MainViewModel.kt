package com.example.autosup.ui.main

import androidx.lifecycle.ViewModel
import com.example.autosup.apiService.ApiClientImpl
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val apiClient = ApiClientImpl()

    suspend fun getAllCarBrands(): Deferred<Response<String>> {
        return apiClient.getAutosupMainHtmlPage()
    }
}