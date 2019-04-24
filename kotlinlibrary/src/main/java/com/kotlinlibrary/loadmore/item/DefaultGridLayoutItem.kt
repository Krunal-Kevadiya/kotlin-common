package com.kotlinlibrary.loadmore.item

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DefaultGridLayoutItem(layoutManager: RecyclerView.LayoutManager) : BaseGridLayoutManagerItem {
    override val spanSize: Int = if (layoutManager is GridLayoutManager) {
        layoutManager.spanCount
    } else {
        1
    }
}
