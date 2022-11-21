package com.packagesmaster.androidhelper.helpers

import android.app.Activity
import androidx.annotation.RestrictTo
import com.packagesmaster.androidhelper.SharedUtils
import com.packagesmaster.androidhelper.ThemeManager
import com.packagesmaster.androidhelper.Utilities

@RestrictTo(RestrictTo.Scope.LIBRARY)
class Helper(var activity: Activity) {

    fun getUtilities(): Utilities {
        return Utilities(activity)
    }

    fun getSharedUtils(): SharedUtils {
        return SharedUtils(activity)
    }

    fun getThemeManager(): ThemeManager {
        return ThemeManager(activity)
    }

}