package com.packagesmaster.androidhelper

import android.app.Activity
import com.packagesmaster.androidhelper.helpers.AndroidHelper


class ThemeManager(var activity: Activity) {

    fun onDarkThemeSelected(onDarkThemeSelected: () -> Unit): ThemeManager {
        if (getCurrentAppTheme() == Constants.AppThemes.Dark.theme) {
            onDarkThemeSelected()
        }
        return this
    }

    fun onLightThemeSelected(onLightThemeSelected: () -> Unit): ThemeManager {
        if (getCurrentAppTheme() == Constants.AppThemes.Light.theme) {
            onLightThemeSelected()
        }
        return this
    }

    fun onDynamicThemeSelected(onDynamicThemeSelected: () -> Unit): ThemeManager {
        if (getCurrentAppTheme() == Constants.AppThemes.Dynamic.theme) {
            onDynamicThemeSelected()
        }
        return this
    }

    fun onAnyThemeSelected(onAnyThemeSelected: () -> Unit): ThemeManager {
        onAnyThemeSelected()
        return this
    }

    fun isDarkModeEnabled(): Boolean {
        if (AndroidHelper.with(activity).getSharedUtils().getSharedPreferences().getInt(Constants.SharedPreferencesKeys.AppTheme.key, Constants.AppThemes.Dark.theme) == Constants.AppThemes.Dark.theme) {
            return true
        }
        return false
    }

    fun getCurrentAppTheme(): Int {
        return AndroidHelper.with(activity).getSharedUtils().getSharedPreferences().getInt(Constants.SharedPreferencesKeys.AppTheme.key, Constants.AppThemes.Dark.theme)
    }

}