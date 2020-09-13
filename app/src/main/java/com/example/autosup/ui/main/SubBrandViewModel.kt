package com.example.autosup.ui.main

import androidx.lifecycle.ViewModel
import com.example.autosup.apiService.ApiClientImpl
import kotlinx.coroutines.Deferred
import retrofit2.Response

class SubBrandViewModel : ViewModel() {

    private val apiClient = ApiClientImpl()

    suspend fun getAllCarSubBrands(url :String?): Deferred<Response<String>> {
        url?.let { return apiClient.getCarBrandHtmlPage(url) }
        return apiClient.getCarBrandHtmlPage("/")
    }
}