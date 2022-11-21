package com.packagesmaster.androidhelper.imagepicker.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.packagesmaster.androidhelper.imagepicker.constant.ImageProvider
import com.packagesmaster.androidhelper.imagepicker.listener.DismissListener
import com.packagesmaster.androidhelper.imagepicker.listener.ResultListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.packagesmaster.androidhelper.R
import com.packagesmaster.androidhelper.helpers.AndroidHelper

/**
 * Show Dialog
 */
internal object DialogHelper {

    /**
     * Show Image Provide Picker Dialog. This will streamline the code to pick/capture image
     *
     */
    fun showChooseAppDialog(
        context: Context,
        listener: ResultListener<ImageProvider>,
        dismissListener: DismissListener?
    ) {
        val imagePickerDialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog_choose_app, null, false)
        AndroidHelper.with(context as Activity).getThemeManager().onLightThemeSelected {
            imagePickerDialogLayout.findViewById<MaterialTextView>(R.id.tvImagePickerTitle).setTextColor(ResourcesCompat.getColor(context.resources, R.color.bold_text_color_light, null))
            imagePickerDialogLayout.findViewById<ConstraintLayout>(R.id.clDialogChooseImagePickerApp).setBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.image_picker_background_color_light, null))
            imagePickerDialogLayout.findViewById<MaterialButton>(R.id.lytCameraPick).setTextColor(ResourcesCompat.getColor(context.resources, R.color.image_picker_text_color_light, null))
            imagePickerDialogLayout.findViewById<MaterialButton>(R.id.lytCameraPick).setRippleColorResource(R.color.white)
            imagePickerDialogLayout.findViewById<MaterialButton>(R.id.lytGalleryPick).setTextColor(ResourcesCompat.getColor(context.resources, R.color.image_picker_text_color_light, null))
            imagePickerDialogLayout.findViewById<MaterialButton>(R.id.lytGalleryPick).setRippleColorResource(R.color.white)
        }
        .onDarkThemeSelected {
            imagePickerDialogLayout.findViewById<MaterialTextView>(R.id.tvImagePickerTitle).setTextColor(ResourcesCompat.getColor(context.resources, R.color.bold_text_color_dark, null))
            imagePickerDialogLayout.findViewById<ConstraintLayout>(R.id.clDialogChooseImagePickerApp).setBackgroundColor(ResourcesCompat.getColor(context.resources, R.color.image_picker_background_color_dark, null))
            imagePickerDialogLayout.findViewById<MaterialButton>(R.id.lytCameraPick).setTextColor(ResourcesCompat.getColor(context.resources, R.color.image_picker_text_color_dark, null))
            imagePickerDialogLayout.findViewById<MaterialButton>(R.id.lytCameraPick).setRippleColorResource(R.color.white)
            imagePickerDialogLayout.findViewById<MaterialButton>(R.id.lytGalleryPick).setTextColor(ResourcesCompat.getColor(context.resources, R.color.image_picker_text_color_dark, null))
            imagePickerDialogLayout.findViewById<MaterialButton>(R.id.lytGalleryPick).setRippleColorResource(R.color.white)
        }
        .onDynamicThemeSelected {

        }
        val imagePickerDialog = MaterialAlertDialogBuilder(context)
            .setView(imagePickerDialogLayout)
            .setCancelable(true)
            .setOnCancelListener {
                listener.onResult(null)
            }
            .setOnDismissListener {
                dismissListener?.onDismiss()
            }
            .show()

        // Handle Camera option click
        imagePickerDialogLayout.findViewById<View>(R.id.lytCameraPick).setOnClickListener {
            listener.onResult(ImageProvider.CAMERA)
            imagePickerDialog.dismiss()
        }

        // Handle Gallery option click
        imagePickerDialogLayout.findViewById<View>(R.id.lytGalleryPick).setOnClickListener {
            listener.onResult(ImageProvider.GALLERY)
            imagePickerDialog.dismiss()
        }
    }
}
