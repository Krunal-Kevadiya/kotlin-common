package com.kotlinlibrary.loadmore

interface ILoadMore {
    fun onLoadMoreBegin(loadMessage: String? = null)

    fun onLoadMoreSucceed(hasMoreItems: Boolean, noMoreMessage: String? = null)

    fun onLoadMoreFailed(failMessage: String? = null)

    fun resetLoadMore()
}