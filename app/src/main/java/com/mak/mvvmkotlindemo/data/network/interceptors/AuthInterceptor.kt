package com.mak.mvvmkotlindemo.data.network.interceptors

import com.mak.mvvmkotlindemo.data.preferences.PreferenceProvider
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val prefs: PreferenceProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val auth = "Basic <ADD TOKEN HERE>"
        request = request.newBuilder()
            .addHeader("Authorization", auth)
            .build()

        return chain.proceed(request)
    }
}