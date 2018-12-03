package com.kotlinlibrary.loadmore

import androidx.recyclerview.widget.RecyclerView

class MyAdapterDataObserver(private val loadMoreWrapper: LoadMoreWrapper) : RecyclerView.AdapterDataObserver() {

    override fun onChanged() {
        loadMoreWrapper.notifyDataSetChanged()
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        loadMoreWrapper.notifyItemRangeRemoved(positionStart, itemCount)
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        loadMoreWrapper.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        loadMoreWrapper.notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        loadMoreWrapper.notifyItemRangeChanged(positionStart, itemCount)
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
        loadMoreWrapper.notifyItemRangeChanged(positionStart, itemCount, payload)
    }
}