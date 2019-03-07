package com.kotlinlibrary.loadmore

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

fun isOnBottom(recyclerView: RecyclerView?, dx: Int, dy: Int, triggerThreshold: Int): Boolean {

    val layoutManager = recyclerView?.layoutManager

    return when (layoutManager) {
        is LinearLayoutManager -> {
            LinearLayoutManagerImpl(layoutManager).isOnBottom(triggerThreshold)
        }
        is GridLayoutManager -> {
            GridLayoutManagerImpl(layoutManager).isOnBottom(triggerThreshold)
        }
        is StaggeredGridLayoutManager -> {
            StaggeredLayoutManagerImpl(layoutManager).isOnBottom(triggerThreshold)
        }
        else -> {
            false
        }
    }
}

fun isOnUp(recyclerView: RecyclerView?, dx: Int, dy: Int, triggerThreshold: Int): Boolean {

    val layoutManager = recyclerView?.layoutManager

    return when (layoutManager) {
        is LinearLayoutManager -> {
            LinearLayoutManagerImpl(layoutManager).isOnUp(triggerThreshold)
        }
        is GridLayoutManager -> {
            GridLayoutManagerImpl(layoutManager).isOnUp(triggerThreshold)
        }
        is StaggeredGridLayoutManager -> {
            StaggeredLayoutManagerImpl(layoutManager).isOnUp(triggerThreshold)
        }
        else -> {
            false
        }
    }
}