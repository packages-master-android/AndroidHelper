package com.packagesmaster.androidhelper.gestures

import android.app.Activity
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs


open class GesturesHandler(activity: Activity) : OnTouchListener {

    private val gestureDetector: GestureDetector

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
//        try {
//            return gestureDetector.onTouchEvent(p1!!)
//        }
//        catch (e: Exception) { }
        return false
    }

    init {
        gestureDetector = GestureDetector(activity, GestureListener())
    }

    open fun onSwipeRight() {}
    open fun onSwipeLeft() {}
    open fun onSwipeTop() {}
    open fun onSwipeBottom() {}

    private inner class GestureListener : SimpleOnGestureListener() {

        private val swipeThreshold = 100
        private val swipeVelocityThreshold = 100

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (abs(diffX) > abs(diffY)) {
                    if (abs(diffX) > swipeThreshold && abs(velocityX) > swipeVelocityThreshold) {
                        if (diffX > 0) {
                            onSwipeRight()
                        }
                        else {
                            onSwipeLeft()
                        }
                        result = true
                    }
                }
                else if (abs(diffY) > swipeThreshold && abs(velocityY) > swipeVelocityThreshold) {
                    if (diffY > 0) {
                        onSwipeBottom()
                    }
                    else {
                        onSwipeTop()
                    }
                    result = true
                }
            }
            catch (e: Exception) {
                Log.e("msg_error", e.message.toString())
            }
            return result
        }
    }

}