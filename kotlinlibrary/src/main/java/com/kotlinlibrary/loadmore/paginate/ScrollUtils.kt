package com.kotlinlibrary.loadmore.paginate

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

internal object ScrollUtils {

    fun isOnBottom(recyclerView: RecyclerView, loadingTriggerThreshold: Int): Boolean {
        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager!!.itemCount
        val firstVisibleItemPosition = getFirstVisibleItemPositionByLayoutManager(recyclerView.layoutManager!!)
        // Check if end of the list is reached (counting threshold) or if there is no items at all
        return totalItemCount - visibleItemCount <= firstVisibleItemPosition + loadingTriggerThreshold || totalItemCount == 0
    }

    private fun getFirstVisibleItemPositionByLayoutManager(layoutManager: RecyclerView.LayoutManager): Int {
        val firstVisibleItemPosition: Int
        firstVisibleItemPosition = if (layoutManager is LinearLayoutManager) {
            layoutManager.findFirstVisibleItemPosition()
        } else if (layoutManager is StaggeredGridLayoutManager) {
            // https://code.google.com/p/android/issues/detail?id=181461
            if (layoutManager.getChildCount() > 0) {
                layoutManager.findFirstVisibleItemPositions(null)[0]
            } else {
                0
            }
        } else {
            throw IllegalStateException("LayoutManager needs to subclass LinearLayoutManager or StaggeredGridLayoutManager")
        }
        return firstVisibleItemPosition
    }

    fun fullScrollToBottom(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.layoutManager!!.smoothScrollToPosition(recyclerView, null, adapter.itemCount - 1)
    }
}
