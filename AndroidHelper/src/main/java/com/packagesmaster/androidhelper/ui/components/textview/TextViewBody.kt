package com.packagesmaster.androidhelper.ui.components.textview

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textview.MaterialTextView
import com.packagesmaster.androidhelper.R
import com.packagesmaster.androidhelper.helpers.AndroidHelper

class TextViewBody @JvmOverloads constructor(context: Context, var attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : MaterialTextView(context, attrs, defStyleAttr, defStyleRes) {

    init {
        if (!isInEditMode) {
            initTextViewBodySettings()
        }
    }

    private fun initTextViewBodySettings() {
        if (attrs?.getAttributeValue("http://schemas.android.com/apk/res/android", "textColor") == null) {
            initTextColor()
        }
    }

    private fun initTextColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            setTextColor(ResourcesCompat.getColor(resources, R.color.body_color_light, null))
        }
        .onDarkThemeSelected {
            setTextColor(ResourcesCompat.getColor(resources, R.color.body_color_dark, null))
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

}