package com.packagesmaster.androidhelper.layoutmanagers

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyCustomGridLayoutContentWrapper(context: Context, spanCount: Int): GridLayoutManager(context, spanCount) {

    constructor(context: Context, spanCount: Int, orientation: Int, reverseLayout: Boolean): this(context, spanCount) {
        this.orientation = orientation
        this.reverseLayout = reverseLayout
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        }
        catch (e: Exception) {}
    }

}