package com.packagesmaster.androidhelper

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.NestedScrollView
import com.packagesmaster.androidhelper.helpers.AndroidHelper
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

class ImagesCaptureHandler {

    private var sharedImageUri: Uri? = null
    private var shareResultLauncher: ActivityResultLauncher<Intent>? = null
    private var isAllowedToStore = false
    private var onImageShared = {}

    /**
     * New Functions - Under Development
     */

    fun shareBitmap(activity: Activity, view: View, isScrollable: Boolean, isShareable: Boolean, isAllowedToStore: Boolean = false, isWantViewToBeGone: Boolean = true, onImageShared: () -> Unit, vararg nonNeededViews: View) {
        this.isAllowedToStore = isAllowedToStore
        this.onImageShared = onImageShared
        for (nonNeededView in nonNeededViews) {
            if (isWantViewToBeGone) {
                nonNeededView.visibility = View.GONE
            }
            else {
                nonNeededView.visibility = View.INVISIBLE
            }
            if (nonNeededView == nonNeededViews.last()) {
                setSharedBitmapSettings(activity, view, isScrollable, isShareable)
            }
        }
        if (nonNeededViews.isEmpty()) {
            setSharedBitmapSettings(activity, view, isScrollable, isShareable)
        }
    }

    private fun setSharedBitmapSettings(activity: Activity, view: View, isScrollable: Boolean, isShareable: Boolean) {
        Handler(Looper.getMainLooper()).postDelayed({
            val width: Int
            val height: Int
            if (isScrollable && view is NestedScrollView) {
                width = view.getChildAt(0).width
                height = view.getChildAt(0).height
            }
            else {
                width = view.width
                height = view.height
            }
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawColor(ResourcesCompat.getColor(activity.resources, if (AndroidHelper.with(activity).getThemeManager().isDarkModeEnabled()) { R.color.color_primary_light } else { R.color.white }, null))
            view.draw(canvas)
            if (isShareable) {
                shareBitmap(activity, bitmap)
            }
            else if (isAllowedToStore) {
                saveBitmapToGallery(activity, bitmap)
            }
        }, 1000)
    }

    private fun loadBitmap(activity: Activity, view: View, isScrollable: Boolean): Bitmap? {
        val width: Int
        val height: Int
        if (isScrollable && view is NestedScrollView) {
            width = view.getChildAt(0).width
            height = view.getChildAt(0).height
        }
        else {
            width = view.width
            height = view.height
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(ResourcesCompat.getColor(activity.resources, if (AndroidHelper.with(activity).getThemeManager().isDarkModeEnabled()) { R.color.color_primary_light } else { R.color.white }, null))
        view.draw(canvas)
        return bitmap
    }

    private fun saveBitmapToCache(activity: Activity, bitmap: Bitmap): Uri {
        var imageNameWithExtension = ""
        try {
            imageNameWithExtension = "shared_age_result.png"
            val cachePath = File(activity.cacheDir, "shared_age_results")
            cachePath.mkdirs()
            val stream = FileOutputStream("$cachePath/$imageNameWithExtension")
            bitmap.compress(Bitmap.CompressFormat.JPEG, 200, stream)
            stream.close()
        } catch (e: Exception) {
        }
        val imagePath = File(activity.cacheDir, "shared_age_results")
        val newFile = File(imagePath, imageNameWithExtension)
        val contentUri = FileProvider.getUriForFile(activity, activity.applicationContext.packageName + ".provider", newFile)
        activity.grantUriPermission(activity.packageName, contentUri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        return contentUri
    }

    private fun saveBitmapToGallery(activity: Activity, bitmap: Bitmap, imageName: String = "${activity.packageName}_${Calendar.getInstance().timeInMillis}"): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(activity.contentResolver, bitmap, imageName, null)
        return Uri.parse(path)
    }

    private fun deleteImageFromGallery(activity: Activity, imageUri: Uri) {
        try {
            activity.contentResolver.delete(imageUri, null, null)
        }
        catch (e: Exception) { }
    }

    private fun shareBitmap(activity: Activity, bitmap: Bitmap) {
        sharedImageUri = saveBitmapToGallery(activity, bitmap)
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_STREAM, sharedImageUri)
        shareIntent.type = "image/*"
        shareResultLauncher!!.launch(Intent.createChooser(shareIntent, activity.resources.getString(R.string.choose_an_app_to_share_the_image)))
    }

    fun initShareResultLauncher(activity: Activity) {
        setShareResultLauncher(activity)
    }

    private fun setShareResultLauncher(activity: Activity) {
        shareResultLauncher = (activity as AppCompatActivity).registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_CANCELED) {
                if (!isAllowedToStore) {
                    deleteImageFromGallery(activity, sharedImageUri!!)
                }
                sharedImageUri = null
                onImageShared()
            }
        }
    }

    fun blurScreenShotBitmap(activity: Activity, view: View, radius: Float, isScrollable: Boolean): Bitmap {
        val bitmap = loadBitmap(activity, view, isScrollable)
            ?: return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        val rs = RenderScript.create(activity)
        val input = Allocation.createFromBitmap(rs, bitmap)
        val output = Allocation.createTyped(rs, input.type)
        val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        script.setRadius(radius)
        script.setInput(input)
        script.forEach(output)
        output.copyTo(bitmap)
        return bitmap
    }

    fun getScreenShotBitmap(activity: Activity, view: View, isScrollable: Boolean): Bitmap {
        return loadBitmap(activity, view, isScrollable)
            ?: return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    }

}