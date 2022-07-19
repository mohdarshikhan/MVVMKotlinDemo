package com.mak.mvvmkotlindemo.data.repositories

import com.mak.mvvmkotlindemo.data.network.AppApi
import com.mak.mvvmkotlindemo.data.network.SafeApiRequest
import com.mak.mvvmkotlindemo.data.network.responses.GoogleBooksVolumesResponse
import com.mak.mvvmkotlindemo.data.preferences.PreferenceProvider

class BookRepository(private val api: AppApi, private val prefs: PreferenceProvider) : SafeApiRequest() {

    // Function that makes the network request, blocking the current thread
    suspend fun getBookVolumes(query: String): GoogleBooksVolumesResponse {
        return apiRequest { api.getBookVolumes(query) }
    }
}