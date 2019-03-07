package com.kotlinlibrary.loadmore

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

interface ILayoutManager {
    fun isOnBottom(triggerThreshold: Int): Boolean
    fun isOnUp(triggerThreshold: Int): Boolean
}

class LinearLayoutManagerImpl(private val linearLayoutManager: LinearLayoutManager) : ILayoutManager {
    override fun isOnBottom(triggerThreshold: Int): Boolean {
        val lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()
        val totalCount = linearLayoutManager.itemCount
        return lastCompletelyVisibleItemPosition >= totalCount - (1 + triggerThreshold)
    }

    override fun isOnUp(triggerThreshold: Int): Boolean {
        val firstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
        return firstCompletelyVisibleItemPosition <= triggerThreshold
    }
}

class GridLayoutManagerImpl(private val gridLayoutManager: GridLayoutManager) : ILayoutManager {
    override fun isOnBottom(triggerThreshold: Int): Boolean {
        val lastCompletelyVisibleItemPosition = gridLayoutManager.findLastCompletelyVisibleItemPosition()
        val totalCount = gridLayoutManager.itemCount
        return lastCompletelyVisibleItemPosition >= totalCount - (1 + triggerThreshold)
    }

    override fun isOnUp(triggerThreshold: Int): Boolean {
        val firstCompletelyVisibleItemPosition = gridLayoutManager.findFirstCompletelyVisibleItemPosition()
        return firstCompletelyVisibleItemPosition <= triggerThreshold
    }
}

class StaggeredLayoutManagerImpl(private val staggeredGridLayoutManager: StaggeredGridLayoutManager) : ILayoutManager {
    override fun isOnBottom(triggerThreshold: Int): Boolean {
        val lastCompletelyVisibleItemPosition = staggeredGridLayoutManager.findLastCompletelyVisibleItemPosition()
        val totalCount = staggeredGridLayoutManager.itemCount
        return lastCompletelyVisibleItemPosition >= totalCount - (1 + triggerThreshold)
    }

    override fun isOnUp(triggerThreshold: Int): Boolean {
        val firstCompletelyVisibleItemPosition = staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions()
        return firstCompletelyVisibleItemPosition <= triggerThreshold
    }
}


