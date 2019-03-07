package com.kotlinlibrary.loadmore

import android.content.Context
import android.util.Log
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadMore private constructor(val builder: Builder) : ILoadMore {
    private var isFailed: Boolean = false
    private var isLoading: Boolean = false
    private var hasMore: Boolean = true
    private var context: Context = builder.context
    private var recyclerView: RecyclerView = builder.recyclerView
    private var loadMoreListener: () -> Unit = builder.loadMoreListener
    @LoadMoreSide private var loadMoreSides: Int = builder.loadMoreSides
    private var loadingTriggerThreshold: Int = builder.loadingTriggerThreshold
    private var isLoginProgressBarVisible: Boolean = builder.isLoginProgressBarVisible
    private var customView: ((RelativeLayout, TextView, ProgressBar) -> Unit)? = builder.customView
    private var userAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> = recyclerView.adapter!!
    private var myAdapterDataObserver: RecyclerView.AdapterDataObserver
    private var loadMoreWrapper: LoadMoreWrapper

    private val scrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (isOnBottom(recyclerView, dx, dy, loadingTriggerThreshold) && loadMoreSides == LoadMoreSides.DOWN_SIDE) {
                onReachLoading()
            } else if (isOnUp(recyclerView, dx, dy, loadingTriggerThreshold) && loadMoreSides == LoadMoreSides.UP_SIDE) {
                onReachLoading()
            }
        }
    }

    init {
        loadMoreWrapper = LoadMoreWrapper(context, Status.Idle, loadMoreSides, isLoginProgressBarVisible, userAdapter, customView)
        myAdapterDataObserver = MyAdapterDataObserver(loadMoreWrapper)

        if (builder.recyclerView.layoutManager is GridLayoutManager) {
            (builder.recyclerView.layoutManager as GridLayoutManager).spanSizeLookup =
                    object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return if (position == userAdapter.itemCount && loadMoreSides == LoadMoreSides.DOWN_SIDE) {
                                (builder.recyclerView.layoutManager as GridLayoutManager).spanCount
                            } else if (position == 0 && loadMoreSides == LoadMoreSides.UP_SIDE) {
                                (builder.recyclerView.layoutManager as GridLayoutManager).spanCount
                            } else {
                                1
                            }
                        }
                    }
        }

        setupLoadMoreWrapper()
        setupScrollListener()
    }

    private fun setupLoadMoreWrapper() {
        userAdapter.registerAdapterDataObserver(myAdapterDataObserver)
        recyclerView.adapter = loadMoreWrapper
    }

    private fun setupScrollListener() {
        recyclerView.addOnScrollListener(scrollListener)
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
        Log.e("LoadMore", "$isFailed , $isLoading , $hasMore")
        loadMoreListener()
    }

    override fun onLoadMoreBegin(isLoginProgressBarVisible: Boolean, loadMessage: String?) {
        isFailed = false
        isLoading = true
        val status = Status.Loading
        status.title = loadMessage
        loadMoreWrapper.setLoginProgressBarVisible(isLoginProgressBarVisible)
        loadMoreWrapper.notifyStatusChanged(status)
    }

    override fun onLoadMoreSucceed(hasMoreItems: Boolean, isLoginProgressBarVisible: Boolean, noMoreMessage: String?) {
        isFailed = false
        isLoading = false
        hasMore = hasMoreItems

        if (!hasMoreItems) {
            val status = Status.NoMore
            status.title = noMoreMessage
            loadMoreWrapper.setLoginProgressBarVisible(isLoginProgressBarVisible)
            loadMoreWrapper.notifyStatusChanged(status)
        }
    }

    override fun onLoadMoreFailed(isLoginProgressBarVisible: Boolean, failMessage: String?) {
        isFailed = true
        isLoading = false
        val status = Status.Error
        status.title = failMessage
        loadMoreWrapper.setLoginProgressBarVisible(isLoginProgressBarVisible)
        loadMoreWrapper.notifyStatusChanged(status)
    }

    override fun resetLoadMore() {
        isFailed = false
        isLoading = false
        hasMore = true
        loadMoreWrapper.notifyStatusChanged(Status.Idle)
    }

    override fun getLoadMoreSide(): Int {
        return loadMoreSides
    }

    class Builder(internal val context: Context) {
        internal var loadingTriggerThreshold: Int = 0
        internal lateinit var recyclerView: RecyclerView
        internal lateinit var loadMoreListener: () -> Unit
        internal var isLoginProgressBarVisible: Boolean = true
        @LoadMoreSide internal var loadMoreSides: Int = LoadMoreSides.DOWN_SIDE
        internal var customView: ((RelativeLayout, TextView, ProgressBar) -> Unit)? = null

        fun setRecyclerView(recyclerView: RecyclerView): Builder {
            this.recyclerView = recyclerView
            return this
        }

        fun setTriggerThreshold(loadingTriggerThreshold: Int): Builder {
            this.loadingTriggerThreshold = loadingTriggerThreshold
            return this
        }

        fun setLoginProgressBarVisible(isLoginProgressBarVisible: Boolean): Builder {
            this.isLoginProgressBarVisible = isLoginProgressBarVisible
            return this
        }

        fun setLoadMoreSide(@LoadMoreSide loadMoreSides: Int): Builder {
            this.loadMoreSides = loadMoreSides
            return this
        }

        fun setCustomView(customView: ((RelativeLayout, TextView, ProgressBar) -> Unit)?): Builder {
            this.customView = customView
            return this
        }

        fun setLoadMoreListener(loadMoreListener: () -> Unit): Builder {
            this.loadMoreListener = loadMoreListener
            return this
        }

        fun build(): ILoadMore {
            return LoadMore(this)
        }
    }
}