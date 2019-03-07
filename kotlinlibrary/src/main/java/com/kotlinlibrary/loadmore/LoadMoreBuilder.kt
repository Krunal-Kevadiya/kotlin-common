package com.kotlinlibrary.loadmore

import android.content.Context
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LoadMoreBuilder {
    lateinit var context: Context
    var triggerThreshold: Int = 0
    var isErrorVisible: Boolean = true
    var isProgressVisible: Boolean = true
    lateinit var recyclerViews: RecyclerView
    var loadMoreSides: LoadMoreSide = LoadMoreSide.DOWN_SIDE
    lateinit var loadMoreListener: () -> Unit
    var customView: ((RelativeLayout, TextView, ProgressBar) -> Unit)? = null
}