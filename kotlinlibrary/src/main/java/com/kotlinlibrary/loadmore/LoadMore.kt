package com.kotlinlibrary.loadmore

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadMore private constructor(val builder: Builder) : ILoadMore {
    private var isFailed: Boolean = false
    private var isLoading: Boolean = false
    private var hasMore: Boolean = true
    private var context: Context
    private var recyclerView: RecyclerView
    private var loadMoreSides: Int = LoadMoreSides.DOWN_SIDE
    private var loadMoreListener: () -> Unit
    private var customView: ((LinearLayout, TextView) -> Unit)?
    private var userAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    private var loadMoreWrapper: LoadMoreWrapper
    private var myAdapterDataObserver: RecyclerView.AdapterDataObserver

    private val scrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (isOnBottom(recyclerView, dx, dy)) {
                onReachBottom()
            }
        }
    }

    init {
        context = builder.context

        recyclerView = builder.recyclerView

        loadMoreSides = builder.loadMoreSides

        loadMoreListener = builder.loadMoreListener

        customView = builder.customView

        userAdapter = recyclerView.adapter!!

        loadMoreWrapper = LoadMoreWrapper(context, userAdapter, Status.Idle, customView)

        myAdapterDataObserver = MyAdapterDataObserver(loadMoreWrapper)

        if (builder.recyclerView.layoutManager is GridLayoutManager) {
            (builder.recyclerView.layoutManager as GridLayoutManager).spanSizeLookup =
                    object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return if (position == userAdapter.itemCount && loadMoreSides == LoadMoreSides.DOWN_SIDE) {
                                (builder.recyclerView.layoutManager as GridLayoutManager).spanCount
                            } else if (position == 0 && loadMoreSides == LoadMoreSides.DOWN_SIDE) {
                                (builder.recyclerView.layoutManager as GridLayoutManager).spanCount
                            } else{
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

    private fun onReachBottom() {
        if (isFailed) {
            return
        }

        if (isLoading) {
            return
        }

        if (!hasMore) {
            return
        }
        loadMoreListener()
    }

    override fun onLoadMoreBegin(loadMessage: String?) {
        isFailed = false
        isLoading = true
        val status = Status.Loading
        loadMessage?.let { msg ->
            status.title = msg
        }
        loadMoreWrapper.notifyStatusChanged(status)
    }

    override fun onLoadMoreSucceed(hasMoreItems: Boolean, noMoreMessage: String?) {
        isFailed = false
        isLoading = false
        hasMore = hasMoreItems

        if (!hasMoreItems) {
            val status = Status.NoMore
            noMoreMessage?.let { msg ->
                status.title = msg
            }
            loadMoreWrapper.notifyStatusChanged(status)
        }
    }

    override fun onLoadMoreFailed(failMessage: String?) {
        isFailed = true
        isLoading = false
        val status = Status.Error
        failMessage?.let { msg ->
            status.title = msg
        }
        loadMoreWrapper.notifyStatusChanged(status)
    }

    override fun resetLoadMore() {
        isFailed = false
        isLoading = false
        hasMore = true
        loadMoreWrapper.notifyStatusChanged(Status.Idle)
    }

    class Builder(internal val context: Context) {
        internal var loadMoreSides: Int = LoadMoreSides.DOWN_SIDE
        internal lateinit var recyclerView: RecyclerView
        internal lateinit var loadMoreListener: () -> Unit
        internal var customView: ((LinearLayout, TextView) -> Unit)? = null

        fun setRecyclerView(recyclerView: RecyclerView): Builder {
            this.recyclerView = recyclerView
            return this
        }

        fun setLoadMoreSide(@LoadMoreSide loadMoreSides: Int): Builder {
            this.loadMoreSides = loadMoreSides
            return this
        }

        fun setCustomView(customView: ((LinearLayout, TextView) -> Unit)?): Builder {
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