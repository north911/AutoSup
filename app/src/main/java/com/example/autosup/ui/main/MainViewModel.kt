package com.example.autosup.ui.main

import androidx.lifecycle.ViewModel
import com.example.autosup.Dagger.ApplicationGraph
import com.example.autosup.Dagger.DaggerApplicationGraph
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MainViewModel : ViewModel() {

    val applicationGraph: ApplicationGraph = DaggerApplicationGraph.create()
    var apiClient = applicationGraph.getApiClient()

    suspend fun getAllCarBrands(): Deferred<Response<String>> {
        return apiClient.getAutosupMainHtmlPage()
    }
}