package com.kotlinlibrary.loadmore

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadMore constructor(option: LoadMoreBuilder.()-> Unit) : ILoadMore {
    private var hasMore: Boolean = true
    private var isFailed: Boolean = false
    private var isLoading: Boolean = false

    private var builder = LoadMoreBuilder()
    private var loadMoreWrapper: LoadMoreWrapper
    private var myAdapterDataObserver: RecyclerView.AdapterDataObserver
    private var userAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    private var scrollListener: RecyclerView.OnScrollListener

    init {
        configure(option)

        userAdapter = builder.recyclerViews.adapter!!
        loadMoreWrapper = LoadMoreWrapper(
            builder.context, Status.Idle,
            builder.isErrorVisible, builder.isProgressVisible,
            builder.loadMoreSides, userAdapter, builder.customView
        )
        myAdapterDataObserver = MyAdapterDataObserver(loadMoreWrapper)

        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (isOnBottom(recyclerView, dx, dy, builder.triggerThreshold) && builder.loadMoreSides == LoadMoreSide.DOWN_SIDE) {
                    onReachLoading()
                } else if (isOnUp(recyclerView, dx, dy, builder.triggerThreshold) && builder.loadMoreSides == LoadMoreSide.UP_SIDE) {
                    onReachLoading()
                }
            }
        }
        if (builder.recyclerViews.layoutManager is GridLayoutManager) {
            (builder.recyclerViews.layoutManager as GridLayoutManager).spanSizeLookup =
                    object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return if (position == userAdapter.itemCount && builder.loadMoreSides == LoadMoreSide.DOWN_SIDE) {
                                (builder.recyclerViews.layoutManager as GridLayoutManager).spanCount
                            } else if (position == 0 && builder.loadMoreSides == LoadMoreSide.UP_SIDE) {
                                (builder.recyclerViews.layoutManager as GridLayoutManager).spanCount
                            } else {
                                1
                            }
                        }
                    }
        }

        setupLoadMoreWrapper()
        setupScrollListener()
    }

    private fun configure(func: LoadMoreBuilder.() -> Unit) {
        func.invoke(builder)
    }

    private fun setupLoadMoreWrapper() {
        userAdapter.registerAdapterDataObserver(myAdapterDataObserver)
        builder.recyclerViews.adapter = loadMoreWrapper
    }

    private fun setupScrollListener() {
        builder.recyclerViews.addOnScrollListener(scrollListener)
    }

    private fun onReachLoading() {
        if (isFailed) {
            return
        }

        if (isLoading) {
            return
        }

        if (!hasMore) {
            return
        }
        builder.loadMoreListener()
    }

    override fun onLoadMoreBegin(loadMessage: String?) {
        isFailed = false
        isLoading = true
        val status = Status.Loading
        status.title = loadMessage ?: Status.STR_LOADING
        loadMoreWrapper.notifyStatusChanged(status)
    }

    override fun onLoadMoreSucceed(hasMoreItems: Boolean, noMoreMessage: String?) {
        isFailed = false
        isLoading = false
        hasMore = hasMoreItems

        if (!hasMoreItems) {
            val status = Status.NoMore
            status.title = noMoreMessage ?: Status.STR_NO_MORE
            loadMoreWrapper.notifyStatusChanged(status)
        }
    }

    override fun onLoadMoreFailed(failMessage: String?) {
        isFailed = true
        isLoading = false
        val status = Status.Error
        status.title = failMessage ?: Status.STR_ERROR
        loadMoreWrapper.notifyStatusChanged(status)
    }

    override fun resetLoadMore() {
        isFailed = false
        isLoading = false
        hasMore = true
        loadMoreWrapper.notifyStatusChanged(Status.Idle)
    }

    override fun getLoadMoreSide(): LoadMoreSide {
        return builder.loadMoreSides
    }
}