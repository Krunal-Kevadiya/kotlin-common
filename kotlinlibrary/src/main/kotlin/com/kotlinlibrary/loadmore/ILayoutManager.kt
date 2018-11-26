package com.kotlinlibrary.loadmore

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

interface ILayoutManager {
    fun isOnBottom(): Boolean
}

class LinearLayoutManagerImpl(private val linearLayoutManager: LinearLayoutManager) : ILayoutManager {
    override fun isOnBottom(): Boolean {
        val lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
        val totalCount = linearLayoutManager.itemCount
        return lastCompletelyVisibleItemPosition >= totalCount - 1
    }
}

class GridLayoutManagerImpl(private val gridLayoutManager: GridLayoutManager) : ILayoutManager {
    override fun isOnBottom(): Boolean {
        val lastCompletelyVisibleItemPosition = gridLayoutManager.findLastCompletelyVisibleItemPosition()
        val totalCount = gridLayoutManager.itemCount
        return lastCompletelyVisibleItemPosition >= totalCount - 1
    }
}

class StaggeredLayoutManagerImpl(private val staggeredGridLayoutManager: StaggeredGridLayoutManager) : ILayoutManager {
    override fun isOnBottom(): Boolean {
        val lastCompletelyVisibleItemPosition = staggeredGridLayoutManager.findLastCompletelyVisibleItemPosition()
        val totalCount = staggeredGridLayoutManager.itemCount
        return lastCompletelyVisibleItemPosition >= totalCount - 1
    }
}


