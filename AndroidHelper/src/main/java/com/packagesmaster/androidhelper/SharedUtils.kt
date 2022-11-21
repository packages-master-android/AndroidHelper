package com.packagesmaster.androidhelper

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedUtils(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)

    fun getSharedPreferences(): SharedPreferences {
        return sharedPreferences
    }

    fun getSharedPreferencesEditor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

}