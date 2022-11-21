package com.packagesmaster.androidhelper.ui.components.cardview

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.card.MaterialCardView
import com.packagesmaster.androidhelper.R
import com.packagesmaster.androidhelper.helpers.AndroidHelper

class CardViewFilled @JvmOverloads constructor(context: Context, var attrs: AttributeSet? = null, defStyleAttr: Int = 0) : MaterialCardView(context, attrs, defStyleAttr) {

    private var noColorSelected = -1
    private var customCardBackgroundColor = noColorSelected
    private var customCardRippleColor = noColorSelected
    
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardViewFilled)
        customCardBackgroundColor = typedArray.getColor(R.styleable.CardViewFilled_customCardViewFilledBackgroundColor, noColorSelected)
        if (customCardBackgroundColor != noColorSelected) {
            setCardBackgroundColor(customCardBackgroundColor)
        }
        customCardRippleColor = typedArray.getColor(R.styleable.CardViewFilled_customCardViewFilledRippleColor, noColorSelected)
        if (isClickable && isFocusable && isEnabled && customCardRippleColor != noColorSelected) {
            rippleColor = ColorStateList.valueOf(customCardRippleColor)
        }
        else {
            rippleColor = ColorStateList.valueOf(ResourcesCompat.getColor(resources, R.color.transparent, null))
        }
        typedArray.recycle()
        if (!isInEditMode) {
            initCardViewSettings()
        }
    }

    private fun initCardViewSettings() {
        if (attrs?.getAttributeValue("http://schemas.android.com/apk/res/android", "strokeWidth") == null) {
            strokeWidth = 0
        }
        if (attrs?.getAttributeValue("http://schemas.android.com/apk/res/android", "cardElevation") == null) {
            cardElevation = 0f
        }
        if (customCardBackgroundColor == noColorSelected) {
            initCardBackgroundColor()
        }
        if (customCardRippleColor == noColorSelected && isClickable && isFocusable && isEnabled) {
            initRippleColor()
        }
    }

    private fun initCardBackgroundColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            setCardBackgroundColor(ResourcesCompat.getColor(resources, R.color.card_view_filled_background_color_light, null))
        }
        .onDarkThemeSelected {
            setCardBackgroundColor(ResourcesCompat.getColor(resources, R.color.card_view_filled_background_color_dark, null))
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

    private fun initRippleColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            setRippleColorResource(R.color.card_view_filled_ripple_color_light)
        }
        .onDarkThemeSelected {
            setRippleColorResource(R.color.card_view_filled_ripple_color_dark)
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

}