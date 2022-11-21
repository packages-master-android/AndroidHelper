package com.packagesmaster.androidhelper.extensions

import android.view.View
import android.view.ViewGroup

class View {

    companion object {

        fun View?.removeSelf() {
            this ?: return
            val parent = parent as? ViewGroup ?: return
            parent.removeView(this)
        }

    }

}