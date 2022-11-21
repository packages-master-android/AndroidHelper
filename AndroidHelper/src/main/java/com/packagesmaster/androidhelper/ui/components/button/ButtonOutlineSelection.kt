package com.packagesmaster.androidhelper.ui.components.button

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.button.MaterialButton
import com.packagesmaster.androidhelper.R
import com.packagesmaster.androidhelper.helpers.AndroidHelper


class ButtonOutlineSelection @JvmOverloads constructor(context: Context, var attrs: AttributeSet? = null, defStyleAttr: Int = 0) : MaterialButton(context, attrs, defStyleAttr) {

    private var isTouchAnimationEnabled = true
    private var noColorSelected = -1
    private var customButtonRippleColor = noColorSelected
    private var customStrokeColor = noColorSelected

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ButtonOutlineSelection)
        isTouchAnimationEnabled = typedArray.getBoolean(R.styleable.ButtonOutlineSelection_isButtonOutlineSelectionTouchAnimationEnabled, true)
        customButtonRippleColor = typedArray.getColor(R.styleable.ButtonOutlineSelection_customButtonOutlineSelectionRippleColor, noColorSelected)
        if (isEnabled && customButtonRippleColor != noColorSelected) {
            rippleColor = ColorStateList.valueOf(customButtonRippleColor)
        }
        else {
            rippleColor = ColorStateList.valueOf(ResourcesCompat.getColor(resources, R.color.transparent, null))
        }
        customStrokeColor = typedArray.getColor(R.styleable.ButtonOutlineSelection_customButtonOutlineSelectionButtonStrokeColor, noColorSelected)
        if (customStrokeColor != noColorSelected) {
            strokeColor = ColorStateList.valueOf(customStrokeColor)
        }
        else {
            strokeColor = ColorStateList.valueOf(ResourcesCompat.getColor(resources, R.color.button_outline_selection_stroke_color_light, null))
        }
        typedArray.recycle()
        if (!isInEditMode) {
            initButtonOutlineSelectionSettings()
        }
    }

    private fun initButtonOutlineSelectionSettings() {
        if (attrs?.getAttributeValue("http://schemas.android.com/apk/res/android", "textColor") == null) {
            initTextColor()
        }
        if (customButtonRippleColor == noColorSelected && isEnabled) {
            initRippleColor()
        }
        if (customStrokeColor == noColorSelected) {
            initStrokeColor()
        }
    }

    private fun initTextColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            setTextColor(ResourcesCompat.getColor(resources, R.color.button_outline_primary_text_color_light, null))
        }
        .onDarkThemeSelected {
            setTextColor(ResourcesCompat.getColor(resources, R.color.button_outline_primary_text_color_dark, null))
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

    private fun initRippleColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            setRippleColorResource(R.color.button_outline_primary_ripple_color_light)
        }
        .onDarkThemeSelected {
            setRippleColorResource(R.color.button_outline_primary_ripple_color_dark)
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

    private fun initStrokeColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            setStrokeColorResource(R.color.button_outline_selection_stroke_color_light)
        }
        .onDarkThemeSelected {
            setStrokeColorResource(R.color.button_outline_selection_stroke_color_dark)
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (isTouchAnimationEnabled) {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    animate().scaleX(0.95f).scaleY(0.95f).setDuration(500).start()
                }
                MotionEvent.ACTION_UP -> {
                    animate().scaleX(1f).scaleY(1f).setDuration(500).start()
                }
            }
        }
//        when(event?.action) {
//            MotionEvent.ACTION_DOWN -> {
//                setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize * 0.98f)
//            }
//            MotionEvent.ACTION_UP -> {
//                setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize / 0.98f)
//            }
//        }
        return super.onTouchEvent(event)
    }

}