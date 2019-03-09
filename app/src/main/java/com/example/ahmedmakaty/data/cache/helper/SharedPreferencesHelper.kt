package com.example.ahmedmakaty.data.cache.helper

import android.R.id.edit
import android.content.Context
import android.preference.PreferenceManager
import javax.inject.Inject
import android.content.SharedPreferences
import android.R.id.edit

class SharedPreferencesHelper @Inject constructor(var context: Context) {
    var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setValue(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "")
    }

}
