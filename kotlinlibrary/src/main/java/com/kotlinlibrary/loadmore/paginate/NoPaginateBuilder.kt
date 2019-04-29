package com.kotlinlibrary.loadmore.paginate

import androidx.annotation.IntRange
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.loadmore.item.ErrorItem
import com.kotlinlibrary.loadmore.item.LoadingItem

class NoPaginateBuilder {
    @IntRange(from = 0) var loadingTriggerThreshold: Int = 0
    lateinit var recyclerView: RecyclerView
    var loadingItem: LoadingItem = LoadingItem.DEFAULT
    var errorItem: ErrorItem = ErrorItem.DEFAULT
    var direction: Direction = Direction.DOWN
    lateinit var onLoadMore: () -> Unit
}
