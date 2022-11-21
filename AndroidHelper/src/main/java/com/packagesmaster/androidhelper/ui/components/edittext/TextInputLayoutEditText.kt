package com.packagesmaster.androidhelper.ui.components.edittext

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputEditText
import com.packagesmaster.androidhelper.R
import com.packagesmaster.androidhelper.helpers.AndroidHelper

class TextInputLayoutEditText @JvmOverloads constructor(context: Context, var attrs: AttributeSet? = null) : TextInputEditText(context, attrs) {

    init {
        if (!isInEditMode) {
            initTextInputSettings()
        }
    }

    private fun initTextInputSettings() {
        if (attrs?.getAttributeValue("http://schemas.android.com/apk/res/android", "textColor") == null) {
            initTextColor()
        }
    }

    private fun initTextColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            setTextColor(ResourcesCompat.getColor(resources, R.color.bold_text_color_light, null))
        }
        .onDarkThemeSelected {
            setTextColor(ResourcesCompat.getColor(resources, R.color.bold_text_color_dark, null))
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

}