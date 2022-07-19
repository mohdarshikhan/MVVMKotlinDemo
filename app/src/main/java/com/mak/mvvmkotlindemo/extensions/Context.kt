package com.mak.mvvmkotlindemo.extensions

import android.content.Context
import android.content.SharedPreferences
import com.mak.mvvmkotlindemo.utils.Constants.PREFS_KEY

fun Context.getSharedPrefs(): SharedPreferences = getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)