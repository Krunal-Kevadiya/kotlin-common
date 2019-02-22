package com.kotlinlibrary.loadmore

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

fun isOnBottom(recyclerView: RecyclerView?, dx: Int, dy: Int, loadingTriggerThreshold: Int): Boolean {

    val layoutManager = recyclerView?.layoutManager

    return when (layoutManager) {
        is LinearLayoutManager -> {
            LinearLayoutManagerImpl(layoutManager).isOnBottom(loadingTriggerThreshold)
        }
        is GridLayoutManager -> {
            GridLayoutManagerImpl(layoutManager).isOnBottom(loadingTriggerThreshold)
        }
        is StaggeredGridLayoutManager -> {
            StaggeredLayoutManagerImpl(layoutManager).isOnBottom(loadingTriggerThreshold)
        }
        else -> {
            false
        }
    }
}

fun isOnUp(recyclerView: RecyclerView?, dx: Int, dy: Int, loadingTriggerThreshold: Int): Boolean {

    val layoutManager = recyclerView?.layoutManager

    return when (layoutManager) {
        is LinearLayoutManager -> {
            LinearLayoutManagerImpl(layoutManager).isOnUp(loadingTriggerThreshold)
        }
        is GridLayoutManager -> {
            GridLayoutManagerImpl(layoutManager).isOnUp(loadingTriggerThreshold)
        }
        is StaggeredGridLayoutManager -> {
            StaggeredLayoutManagerImpl(layoutManager).isOnUp(loadingTriggerThreshold)
        }
        else -> {
            false
        }
    }
}