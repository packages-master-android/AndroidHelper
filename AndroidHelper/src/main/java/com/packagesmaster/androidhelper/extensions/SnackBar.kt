package com.packagesmaster.androidhelper.extensions

import android.content.res.Resources
import android.view.Gravity
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.packagesmaster.androidhelper.R

class SnackBar {

    companion object {

        fun Snackbar.setMarginsAndCorners(resources: Resources, colorResource: Int = R.color.failed, iconResource: Int = R.drawable.ic_failed) {
            val tvSnackText = this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
//            if (colorResource == R.color.success) {
//                tvSnackText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_success, 0)
//            }
//            else if (colorResource == R.color.failed) {
//                tvSnackText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_failed, 0)
//            }
            tvSnackText.setCompoundDrawablesWithIntrinsicBounds(0, 0, iconResource, 0)
            tvSnackText.compoundDrawablePadding = resources.getDimensionPixelOffset(com.intuit.sdp.R.dimen._10sdp)
            tvSnackText.gravity = Gravity.CENTER or Gravity.START
            val snackBarBackgroundDrawable = ResourcesCompat.getDrawable(resources, R.drawable.snackbar_shape, null)
            this.view.background = snackBarBackgroundDrawable
        }

    }

}