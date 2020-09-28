package com.example.autosup.model

import androidx.lifecycle.ViewModel
import com.example.autosup.Dagger.ApplicationGraph
import com.example.autosup.Dagger.DaggerApplicationGraph
import kotlinx.coroutines.Deferred
import retrofit2.Response

class PartViewModel : ViewModel() {
    private val applicationGraph: ApplicationGraph = DaggerApplicationGraph.create()
    private var apiClient = applicationGraph.getApiClient()

    suspend fun getAllParts(url :String?): Deferred<Response<String>> {
        url?.let { return apiClient.getPartsPage(url) }
        return apiClient.getPartsPage("/")
    }
}