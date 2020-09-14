package com.example.autosup.Dagger

import com.example.autosup.apiService.ApiClientImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationGraph {
    fun getApiClient(): ApiClientImpl
}