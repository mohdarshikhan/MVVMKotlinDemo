package com.mak.mvvmkotlindemo.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.mak.mvvmkotlindemo.extensions.getSharedPrefs
import com.mak.mvvmkotlindemo.utils.Constants.KEY_LOGIN_ACCESS_TOKEN

class PreferenceProvider(
    context: Context
) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = appContext.getSharedPrefs()


    fun saveAccessToken(access_token: String) {
        preference.edit()
            .putString(KEY_LOGIN_ACCESS_TOKEN, access_token)
            .apply()
    }


    fun getAccessToken(): String? {
        return preference.getString(KEY_LOGIN_ACCESS_TOKEN, null)
    }

}