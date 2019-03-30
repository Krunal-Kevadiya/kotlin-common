package com.kotlinlibrary.loadmore

interface ILoadMore {
    fun onLoadMoreBegin(loadMessage: String? = null, block: ()-> Unit)

    fun onLoadMoreSucceed(hasMoreItems: Boolean, noMoreMessage: String? = null, block: ()-> Unit)

    fun onLoadMoreFailed(failMessage: String? = null, block: ()-> Unit)

    fun resetLoadMore()

    fun getLoadMoreSide(): LoadMoreSide
}