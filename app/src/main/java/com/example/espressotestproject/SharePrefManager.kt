package com.example.espressotestproject

import android.content.Context
import android.content.SharedPreferences

object SharePrefManager {

    private const val ID = "ESPRESSO_TEST"
    private const val USER_NAME = "USER_NAME"

    lateinit var sharePref: SharedPreferences

    fun setUpPref(context: Context) {
        sharePref = context.getSharedPreferences(ID, Context.MODE_PRIVATE)
    }

    fun setUsername(value: String) {
        sharePref.edit().putString(USER_NAME, value).apply()
    }

    fun getUsername(): String {
        return sharePref.getString(USER_NAME, "").toString()
    }

    fun clearUsername() {
        sharePref.edit().remove(USER_NAME).apply()
    }
}