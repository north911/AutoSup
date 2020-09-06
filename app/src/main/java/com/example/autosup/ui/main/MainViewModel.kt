package com.example.autosup.ui.main

import androidx.lifecycle.ViewModel
import com.example.autosup.Model.CarBrand
import com.example.autosup.apiService.ApiClientImpl
import com.example.autosup.utils.convertHtmlElementsToArray
import com.example.autosup.utils.getElements
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val apiClient = ApiClientImpl()

    suspend fun getAllCarBrands(): Deferred<Response<String>> {
        val res = apiClient.getAutosupMainHtmlPageAsString()
        return res
    }
}