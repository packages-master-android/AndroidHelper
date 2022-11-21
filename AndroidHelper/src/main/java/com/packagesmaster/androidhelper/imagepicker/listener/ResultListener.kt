package com.packagesmaster.androidhelper.imagepicker.listener

/**
 * Generic Class To Listen Async Result
 */
internal interface ResultListener<T> {

    fun onResult(t: T?)
}
