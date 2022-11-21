package com.packagesmaster.androidhelper.layoutmanagers

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyCustomLinearLayoutContentWrapper(context: Context): LinearLayoutManager(context) {

    constructor(context: Context, orientation: Int, reverseLayout: Boolean): this(context) {
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