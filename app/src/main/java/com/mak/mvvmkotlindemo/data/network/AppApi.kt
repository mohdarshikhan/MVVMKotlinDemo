package com.mak.mvvmkotlindemo.data.network

import com.mak.mvvmkotlindemo.BuildConfig
import com.mak.mvvmkotlindemo.data.network.interceptors.AuthInterceptor
import com.mak.mvvmkotlindemo.data.network.interceptors.NetworkConnectionInterceptor
import com.mak.mvvmkotlindemo.data.network.responses.GoogleBooksVolumesResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface AppApi {

    /**
     * Get books list
     */
    @GET("volumes")
    suspend fun getBookVolumes(@Query("q") query: String): Response<GoogleBooksVolumesResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor,
            authInterceptor: AuthInterceptor
        ): AppApi {

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(authInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AppApi::class.java)
        }

        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor,
        ): AppApi {

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AppApi::class.java)
        }
    }
}