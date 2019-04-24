package com.kotlinlibrary.loadmore.paginate.grid

import androidx.recyclerview.widget.GridLayoutManager
import com.kotlinlibrary.loadmore.item.BaseGridLayoutManagerItem
import com.kotlinlibrary.loadmore.paginate.WrapperAdapter

class WrapperSpanSizeLookup(
    val wrappedSpanSizeLookup: GridLayoutManager.SpanSizeLookup,
    private val loadingListItemSpanLookup: BaseGridLayoutManagerItem,
    private val wrapperAdapter: WrapperAdapter?
) : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
        return if(wrapperAdapter == null) {
            wrappedSpanSizeLookup.getSpanSize(position)
        } else if (wrapperAdapter.isLoadingItem(position) || wrapperAdapter.isErrorItem(position)) {
            loadingListItemSpanLookup.spanSize
        } else {
            wrappedSpanSizeLookup.getSpanSize(position)
        }
    }
}
