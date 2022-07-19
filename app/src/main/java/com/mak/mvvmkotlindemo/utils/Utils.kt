package com.mak.mvvmkotlindemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * This class is to declare common utility functions like check network.
 *
 *
 * Copyright (c) 2018 <ClientName>. All rights reserved.
 * Created by mak on 20/9/18.
</ClientName> */
object Utils {
    /**
     * Check internet working
     *
     * @param context Context
     * @return false if no connection
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (connectivityManager != null) {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        } else {
            false
        }
    }
}