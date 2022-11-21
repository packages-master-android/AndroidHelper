package com.packagesmaster.androidhelper.ui.components.textinput

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputLayout
import com.packagesmaster.androidhelper.R
import com.packagesmaster.androidhelper.helpers.AndroidHelper

class TextInputOutline @JvmOverloads constructor(context: Context, var attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TextInputLayout(context, attrs, defStyleAttr) {

    private var noColorSelected = -1
    private var customHintTextColor = noColorSelected
    private var customBoxStrokeColor = noColorSelected
    private var customErrorTextColor = noColorSelected
    private var customBoxStrokeErrorColor = noColorSelected
    private var customErrorIconTintColor = noColorSelected
    private var customCounterTextColor = noColorSelected
    private var customStartIconTintColor = noColorSelected
    private var customEndIconTintColor = noColorSelected
    private var customHelperTextTextColor = noColorSelected

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextInputOutline)
        customHintTextColor = typedArray.getColor(R.styleable.TextInputOutline_customTextInputOutlineHintTextColor, noColorSelected)
        if (customHintTextColor != noColorSelected) {
            hintTextColor = ColorStateList.valueOf(customHintTextColor)
        }
        customBoxStrokeColor = typedArray.getColor(R.styleable.TextInputOutline_customTextInputOutlineBoxStrokeColor, noColorSelected)
        if (customBoxStrokeColor != noColorSelected) {
            setBoxStrokeColorStateList(ColorStateList.valueOf(customBoxStrokeColor))
        }
        customErrorTextColor = typedArray.getColor(R.styleable.TextInputOutline_customTextInputOutlineErrorTextColor, noColorSelected)
        if (customErrorTextColor != noColorSelected) {
            setErrorTextColor(ColorStateList.valueOf(customErrorTextColor))
        }
        customBoxStrokeErrorColor = typedArray.getColor(R.styleable.TextInputOutline_customTextInputOutlineBoxStrokeErrorColor, noColorSelected)
        if (customBoxStrokeErrorColor != noColorSelected) {
            boxStrokeErrorColor = ColorStateList.valueOf(customBoxStrokeErrorColor)
        }
        customErrorIconTintColor = typedArray.getColor(R.styleable.TextInputOutline_customTextInputOutlineErrorIconTintColor, noColorSelected)
        if (customErrorIconTintColor != noColorSelected) {
            setErrorIconTintList(ColorStateList.valueOf(customErrorIconTintColor))
        }
        customCounterTextColor = typedArray.getColor(R.styleable.TextInputOutline_customTextInputOutlineCounterTextColor, noColorSelected)
        if (customCounterTextColor != noColorSelected) {
            counterTextColor = ColorStateList.valueOf(customCounterTextColor)
        }
        customStartIconTintColor = typedArray.getColor(R.styleable.TextInputOutline_customTextInputOutlineStartIconTintColor, noColorSelected)
        if (customStartIconTintColor != noColorSelected) {
            setStartIconTintList(ColorStateList.valueOf(customStartIconTintColor))
        }
        customEndIconTintColor = typedArray.getColor(R.styleable.TextInputOutline_customTextInputOutlineEndIconTintColor, noColorSelected)
        if (customEndIconTintColor != noColorSelected) {
            setEndIconTintList(ColorStateList.valueOf(customEndIconTintColor))
        }
        customHelperTextTextColor = typedArray.getColor(R.styleable.TextInputOutline_customTextInputOutlineHelperTextTextColor, noColorSelected)
        if (customHelperTextTextColor != noColorSelected) {
            setHelperTextColor(ColorStateList.valueOf(customHelperTextTextColor))
        }

        typedArray.recycle()
        if (!isInEditMode) {
            initTextInputSettings()
        }
    }

    private fun initTextInputSettings() {
        if (attrs?.getAttributeValue("http://schemas.android.com/apk/res/android", "textColorHint") == null) {
            initDefaultHintColor()
        }
        if (customHintTextColor == noColorSelected) {
            initHintTextColor()
        }
        if (customBoxStrokeColor == noColorSelected) {
            initBoxStrokeColor()
        }
        if (customErrorTextColor == noColorSelected) {
            initErrorTextColor()
        }
        if (customBoxStrokeErrorColor == noColorSelected) {
            initBoxStrokeErrorColor()
        }
        if (customErrorIconTintColor == noColorSelected) {
            initErrorIconTintColor()
        }
        if (customCounterTextColor == noColorSelected) {
            initCounterTextColor()
        }
        if (customStartIconTintColor == noColorSelected) {
            initStartIconTintColor()
        }
        if (customEndIconTintColor == noColorSelected) {
            initEndIconTintColor()
        }
        if (customHelperTextTextColor == noColorSelected) {
            initHelperTextTextColor()
        }
    }

    private fun initDefaultHintColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            defaultHintTextColor = ResourcesCompat.getColorStateList(resources, R.color.text_input_outline_default_text_color_hint_light, null)
        }
        .onDarkThemeSelected {
            defaultHintTextColor = ResourcesCompat.getColorStateList(resources, R.color.text_input_outline_default_text_color_hint_dark, null)
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

    private fun initHintTextColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            hintTextColor = ResourcesCompat.getColorStateList(resources, R.color.text_input_outline_text_color_hint_light, null)
        }
        .onDarkThemeSelected {
            hintTextColor = ResourcesCompat.getColorStateList(resources, R.color.text_input_outline_text_color_hint_dark, null)
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

    private fun initBoxStrokeColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            setBoxStrokeColorStateList(ResourcesCompat.getColorStateList(resources, R.color.text_input_layout_stroke_color_light, null)!!)
        }
        .onDarkThemeSelected {
            setBoxStrokeColorStateList(ResourcesCompat.getColorStateList(resources, R.color.text_input_layout_stroke_color_dark, null)!!)
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

    private fun initErrorTextColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {

        }
        .onDarkThemeSelected {

        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {
            setErrorTextColor(ResourcesCompat.getColorStateList(resources, R.color.failed, null))
        }
    }

    private fun initBoxStrokeErrorColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {

        }
        .onDarkThemeSelected {

        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {
            boxStrokeErrorColor = ResourcesCompat.getColorStateList(resources, R.color.failed, null)
        }
    }

    private fun initErrorIconTintColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {

        }
        .onDarkThemeSelected {

        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {
            setErrorIconTintList(ResourcesCompat.getColorStateList(resources, R.color.failed, null))
        }
    }

    private fun initCounterTextColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            counterTextColor = ColorStateList.valueOf(ResourcesCompat.getColor(resources, R.color.text_input_outline_counter_text_color_light, null))
        }
        .onDarkThemeSelected {
            counterTextColor = ColorStateList.valueOf(ResourcesCompat.getColor(resources, R.color.text_input_outline_counter_text_color_dark, null))
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

    private fun initStartIconTintColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            setStartIconTintList(ColorStateList.valueOf(ResourcesCompat.getColor(resources, R.color.text_input_outline_start_icon_tint_color_light, null)))
        }
        .onDarkThemeSelected {
            setStartIconTintList(ColorStateList.valueOf(ResourcesCompat.getColor(resources, R.color.text_input_outline_start_icon_tint_color_dark, null)))
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

    private fun initEndIconTintColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            setEndIconTintList(ColorStateList.valueOf(ResourcesCompat.getColor(resources, R.color.text_input_outline_end_icon_tint_color_light, null)))
        }
        .onDarkThemeSelected {
            setEndIconTintList(ColorStateList.valueOf(ResourcesCompat.getColor(resources, R.color.text_input_outline_end_icon_tint_color_dark, null)))
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

    private fun initHelperTextTextColor() {
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            setHelperTextColor(ResourcesCompat.getColorStateList(resources, R.color.text_input_outline_helper_text_color_light, null))
        }
        .onDarkThemeSelected {
            setHelperTextColor(ResourcesCompat.getColorStateList(resources, R.color.text_input_outline_helper_text_color_dark, null))
        }
        .onDynamicThemeSelected {

        }
        .onAnyThemeSelected {

        }
    }

}