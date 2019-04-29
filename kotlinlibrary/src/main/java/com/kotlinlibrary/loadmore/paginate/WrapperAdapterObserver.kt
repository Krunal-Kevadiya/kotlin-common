package com.kotlinlibrary.loadmore.paginate

import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.loadmore.callback.OnAdapterChangeListener

internal class WrapperAdapterObserver(
    private val adapterChangeListener: OnAdapterChangeListener,
    private val wrapperAdapter: WrapperAdapter?
) : RecyclerView.AdapterDataObserver() {

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        wrapperAdapter?.notifyItemRangeChanged(positionStart, itemCount)
        adapterChangeListener.onAdapterChange()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
        wrapperAdapter?.notifyItemRangeChanged(positionStart, itemCount, payload)
        adapterChangeListener.onAdapterChange()
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        wrapperAdapter?.notifyItemRangeChanged(positionStart, itemCount)
        adapterChangeListener.onAdapterChange()
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        wrapperAdapter?.notifyItemRangeRemoved(positionStart, itemCount)
        adapterChangeListener.onAdapterChange()
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        wrapperAdapter?.notifyItemMoved(fromPosition, toPosition)
        adapterChangeListener.onAdapterChange()
    }

    override fun onChanged() {
        wrapperAdapter?.notifyDataSetChanged()
        adapterChangeListener.onAdapterChange()
    }
}
