package com.kotlinlibrary.loadmore

interface ILoadMore {
    fun onLoadMoreBegin(isLoginProgressBarVisible: Boolean = true, loadMessage: String? = null)

    fun onLoadMoreSucceed(hasMoreItems: Boolean, isLoginProgressBarVisible: Boolean = true, noMoreMessage: String? = null)

    fun onLoadMoreFailed(isLoginProgressBarVisible: Boolean = true, failMessage: String? = null)

    fun resetLoadMore()

    fun getLoadMoreSide(): Int
}