package com.kotlinlibrary.loadmore

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

fun isOnBottom(recyclerView: RecyclerView?, dx: Int, dy: Int): Boolean {

    val layoutManager = recyclerView?.layoutManager

    return when (layoutManager) {

        is LinearLayoutManager -> {
            LinearLayoutManagerImpl(layoutManager).isOnBottom()
        }
        is GridLayoutManager -> {
            GridLayoutManagerImpl(layoutManager).isOnBottom()
        }
        is StaggeredGridLayoutManager -> {
            StaggeredLayoutManagerImpl(layoutManager).isOnBottom()
        }
        else -> {
            false
        }
    }
}