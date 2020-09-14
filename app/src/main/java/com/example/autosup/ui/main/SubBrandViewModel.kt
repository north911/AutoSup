package com.example.autosup.ui.main

import androidx.lifecycle.ViewModel
import com.example.autosup.Dagger.ApplicationGraph
import com.example.autosup.Dagger.DaggerApplicationGraph
import kotlinx.coroutines.Deferred
import retrofit2.Response

class SubBrandViewModel : ViewModel() {

    val applicationGraph: ApplicationGraph = DaggerApplicationGraph.create()
    var apiClient = applicationGraph.getApiClient()

    suspend fun getAllCarSubBrands(url :String?): Deferred<Response<String>> {
        url?.let { return apiClient.getCarBrandHtmlPage(url) }
        return apiClient.getCarBrandHtmlPage("/")
    }
}