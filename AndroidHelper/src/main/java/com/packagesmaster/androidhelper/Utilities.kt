package com.packagesmaster.androidhelper

import android.app.Activity
import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.*
import android.util.Base64
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.RawRes
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import com.facebook.drawee.view.SimpleDraweeView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.packagesmaster.androidhelper.extensions.SnackBar.Companion.setMarginsAndCorners
import java.util.*

@RestrictTo(RestrictTo.Scope.LIBRARY)
class Utilities(var activity: Activity) {

    private var mediaPlayer = MediaPlayer()

    fun showSnackBar(resources: Resources, layout: View, stringResource: Int = R.string.error_occurred, colorResource: Int = R.color.failed, iconResource: Int = R.drawable.ic_failed, anchorView: View? = null) {
        val snackBar = Snackbar.make(layout, resources.getString(stringResource), Snackbar.LENGTH_SHORT)
        snackBar.setBackgroundTint(ResourcesCompat.getColor(resources, colorResource, null))
        snackBar.setMarginsAndCorners(resources, colorResource, iconResource)
        if (anchorView != null) {
            snackBar.anchorView = anchorView
        }
        snackBar.show()
    }

    fun showSnackBarWithAction(resources: Resources, layout: View, stringResource: Int = R.string.error_occurred, colorResource: Int = R.color.failed, iconResource: Int = R.drawable.ic_failed, anchorView: View? = null, actionStringResource: Int = R.string.retry, actionColorResource: Int = R.color.white, action: () -> Unit) {
        val snackBar = Snackbar.make(layout, resources.getString(stringResource), Snackbar.LENGTH_SHORT)
        snackBar.setBackgroundTint(ResourcesCompat.getColor(resources, colorResource, null))
        snackBar.setMarginsAndCorners(resources, colorResource, iconResource)
        if (anchorView != null) {
            snackBar.anchorView = anchorView
        }
        snackBar.setAction(resources.getString(actionStringResource)) {
            action()
        }
        .setActionTextColor(ResourcesCompat.getColor(resources, actionColorResource, null))
        snackBar.show()
    }

    fun showProgressDialog(): AlertDialog {
        val progressDialogLayout = LayoutInflater.from(activity).inflate(R.layout.custom_progress_dialog, null, false)
        val progressDialog = MaterialAlertDialogBuilder(activity)
            .setView(progressDialogLayout)
            .setCancelable(false)
            .show()
        return progressDialog
    }

    fun hideProgressDialog(progressDialog: AlertDialog) {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    fun hideKeyboard() {
        if (activity.currentFocus != null) {
            val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
    }

    fun getDeviceOrientation(): Int {
        val orientation = activity.resources.configuration.orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            return Configuration.ORIENTATION_PORTRAIT
        }
        return Configuration.ORIENTATION_LANDSCAPE
    }

    fun isDeviceInPortraitMode(): Boolean {
        return getDeviceOrientation() == Configuration.ORIENTATION_PORTRAIT
    }

    fun setStatusBarColor(colorResource: Int) {
        activity.window.statusBarColor = ResourcesCompat.getColor(activity.resources, colorResource, null)
    }

    fun setNavigationBarColor(colorResource: Int) {
        activity.window.navigationBarColor = ResourcesCompat.getColor(activity.resources, colorResource, null)
    }

    fun setDynamicStatusBarColor(colorResource: Int? = null) {
        val theme = activity.theme
        val typedValue = TypedValue()
        theme.resolveAttribute(colorResource ?: com.google.android.material.R.attr.colorSurfaceVariant, typedValue, true)
        val color = typedValue.data
        activity.window.statusBarColor = color
    }

    fun setDynamicNavigationBarColor(colorResource: Int? = null) {
        val theme = activity.theme
        val typedValue = TypedValue()
        theme.resolveAttribute(colorResource ?: com.google.android.material.R.attr.colorSurface, typedValue, true)
        val color = typedValue.data
        activity.window.navigationBarColor = color
    }

    fun setBottomSheetBackgroundColor(parentLayout: View, colorResource: Int) {
        val backgroundDrawable = ResourcesCompat.getDrawable(activity.resources, R.drawable.rounded_bottom_sheet, null)!!
        backgroundDrawable.setTint(ResourcesCompat.getColor(activity.resources, colorResource, null))
        parentLayout.background = backgroundDrawable
    }

    fun setDynamicBottomSheetBackgroundColor(parentLayout: View, colorResource: Int? = null) {
        val backgroundDrawable = ResourcesCompat.getDrawable(activity.resources, R.drawable.rounded_bottom_sheet, null)!!
        val theme = activity.theme
        val typedValue = TypedValue()
        theme.resolveAttribute(colorResource ?: com.google.android.material.R.attr.colorSurfaceVariant, typedValue, true)
        val color = typedValue.data
        backgroundDrawable.setTint(color)
        parentLayout.background = backgroundDrawable
    }

    fun setBottomSheetNavigationBarColor(bottomSheetDialog: BottomSheetDialog, colorResource: Int) {
        bottomSheetDialog.window!!.navigationBarColor = ResourcesCompat.getColor(activity.resources, colorResource, null)
    }

    fun setDynamicBottomSheetNavigationBarColor(bottomSheetDialog: BottomSheetDialog, colorResource: Int? = null) {
        val theme = activity.theme
        val typedValue = TypedValue()
        theme.resolveAttribute(colorResource ?: com.google.android.material.R.attr.colorSurfaceVariant, typedValue, true)
        val color = typedValue.data
        bottomSheetDialog.window!!.navigationBarColor = color
    }

    fun makeStatusBarTransparent() {
        activity.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = ResourcesCompat.getColor(activity.resources, R.color.transparent, null)
        }
    }

    fun hideSystemWindows() {
        activity.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    fun returnStatusBarToDefault() {
        activity.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
    fun generateRandomId(): String {
        val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = java.util.Random()
        val sb = StringBuilder(20)
        for (i in 0..19) {
            val randomCharIndex = random.nextInt(chars.length)
            sb.append(chars[randomCharIndex])
        }
        return sb.toString()
    }


    fun generateRandomNumber(): String {
        val chars = "0123456789"
        val random = java.util.Random()
        val sb = StringBuilder(20)
        for (i in 0..19) {
            val randomCharIndex = random.nextInt(chars.length)
            sb.append(chars[randomCharIndex])
        }
        return sb.toString()
    }


    fun convertTimeToString(timeInMillis: Long): String {
        val secondMillis = 1000
        val minuteMillis = 60 * secondMillis
        val hourMillis = 60 * minuteMillis
        val dayMillis = 24 * hourMillis

        var time = timeInMillis
        if (time < 1000000000000L) {
            time *= 1000
        }

        val now = Calendar.getInstance().timeInMillis
        if (time > now || time <= 0) {
            return activity.resources.getString(R.string.in_the_future)
        }

        val diff = now - time
        return when {
            diff < minuteMillis -> activity.resources.getString(R.string.just_now)
            diff < 2 * minuteMillis -> activity.resources.getString(R.string.a_minute_ago)
            diff < 60 * minuteMillis -> activity.resources.getString(R.string.minutes_ago, (diff / minuteMillis).toString())
            diff < 2 * hourMillis -> activity.resources.getString(R.string.an_hour_ago)
            diff < 24 * hourMillis -> activity.resources.getString(R.string.hours_ago, (diff / hourMillis).toString())
            diff < 48 * hourMillis -> activity.resources.getString(R.string.yesterday)
            else -> activity.resources.getString(R.string.days_ago, (diff / dayMillis).toString())
        }
    }

    fun vibrateDevice(timeInMillis: Long) {
        val vibrator = activity.getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(timeInMillis, VibrationEffect.DEFAULT_AMPLITUDE))
            }
            else {
                vibrator.vibrate(timeInMillis)
            }
        }
    }

    fun getCurrentDeviceLanguageCode(): String {
        val deviceLanguage = Locale.getDefault().toLanguageTag()
        if (deviceLanguage.contains(Constants.Language.Arabic.code)) {
            return Constants.Language.Arabic.code
        }
        return Constants.Language.English.code
    }

    fun isDeviceLanguageEnglish(): Boolean {
        val deviceLanguage = Locale.getDefault().toLanguageTag()
        if (deviceLanguage.contains(Constants.Language.Arabic.code)) {
            return false
        }
        return true
    }

    fun playSound(@RawRes soundRaw: Int): MediaPlayer {
        mediaPlayer = MediaPlayer.create(activity, soundRaw)
        mediaPlayer.start()
        return mediaPlayer
    }

    fun isCurrentlyPlayingSound(): Boolean {
        if (mediaPlayer.isPlaying) {
            return true
        }
        return false
    }

    fun animateImageView(imageView: ImageView, imageResource: Int) {
        val animOut = AnimationUtils.loadAnimation(activity, R.anim.splash_fade_exit)
        val animIn = AnimationUtils.loadAnimation(activity, R.anim.splash_fade_enter)
        animOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }
            override fun onAnimationEnd(animation: Animation?) {
                if (imageView is SimpleDraweeView) {
                    imageView.setActualImageResource(imageResource)
                }
                else {
                    imageView.setImageResource(imageResource)
                }
                imageView.startAnimation(animIn)
            }
            override fun onAnimationStart(animation: Animation?) {
            }
        })
        imageView.startAnimation(animOut)
    }

    fun getBitmapFromBase64(encodedString: String): Bitmap {
        // convert base64 as data:image/jpeg;base64, to bitmap
        encodedString.replace("data:image/png;base64,", "")
        val imageBytes = Base64.decode(encodedString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    fun lockOrientation() {
        if (isDeviceInPortraitMode()) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        else {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    fun unlockOrientation() {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    fun preventScreenRecording() {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

    fun allowScreenRecording() {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

}