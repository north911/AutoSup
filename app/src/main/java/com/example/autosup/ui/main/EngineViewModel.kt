package com.example.autosup.ui.main

import androidx.lifecycle.ViewModel
import com.example.autosup.Dagger.ApplicationGraph
import com.example.autosup.Dagger.DaggerApplicationGraph
import kotlinx.coroutines.Deferred
import retrofit2.Response

class EngineViewModel : ViewModel() {

    private val applicationGraph: ApplicationGraph = DaggerApplicationGraph.create()
    private var apiClient = applicationGraph.getApiClient()

    suspend fun getAllEngines(url :String?): Deferred<Response<String>> {
        url?.let { return apiClient.getEnginesHtmlPage(url) }
        return apiClient.getEnginesHtmlPage("/")
    }
}