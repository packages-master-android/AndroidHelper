package com.packagesmaster.androidhelper.helpers

import android.app.Activity

class AndroidHelper {

    companion object {
        fun with(activity: Activity): Helper {
            return Helper(activity)
        }
    }

}