package com.kotlinlibrary.loadmore.paginate

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kotlinlibrary.loadmore.callback.OnAdapterChangeListener
import com.kotlinlibrary.loadmore.callback.OnRepeatListener
import com.kotlinlibrary.loadmore.item.DefaultGridLayoutItem
import com.kotlinlibrary.loadmore.paginate.grid.WrapperSpanSizeLookup

class NoPaginate constructor(option: NoPaginateBuilder.()-> Unit) : OnAdapterChangeListener, OnRepeatListener {
    private var builder = NoPaginateBuilder()
    private var wrapperAdapter: WrapperAdapter? = null
    private var wrapperAdapterObserver: WrapperAdapterObserver? = null
    private lateinit var userAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    private var wrapperSpanSizeLookup: WrapperSpanSizeLookup? = null

    private var isError: Boolean = false
    private var isLoading: Boolean = false
    private var isLoadedAllItems: Boolean = false
    private var scrollListener: RecyclerView.OnScrollListener

    init {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                checkScroll()
            }
        }

        configure(option)
        setupDirection()
        setupWrapper()
        setupScrollListener()
    }

    private val isCanLoadMore: Boolean
        get() = !isLoading && !isError && !isLoadedAllItems

    private fun configure(func: NoPaginateBuilder.() -> Unit) {
        func.invoke(builder)
    }

    private fun setupDirection() {
        if(builder.direction == Direction.UP) {
            when {
                builder.recyclerView.layoutManager is LinearLayoutManager ->
                    (builder.recyclerView.layoutManager as LinearLayoutManager).reverseLayout = true
                builder.recyclerView.layoutManager is GridLayoutManager ->
                    (builder.recyclerView.layoutManager as GridLayoutManager).reverseLayout = true
                builder.recyclerView.layoutManager is StaggeredGridLayoutManager ->
                    (builder.recyclerView.layoutManager as StaggeredGridLayoutManager).reverseLayout = true
                else -> { }
            }
        }
    }

    private fun setupWrapper() {
        this.userAdapter = builder.recyclerView.adapter!!
        wrapperAdapter = WrapperAdapter(userAdapter, builder.loadingItem, builder.errorItem)
        wrapperAdapterObserver = WrapperAdapterObserver(this, wrapperAdapter)
        userAdapter.registerAdapterDataObserver(wrapperAdapterObserver!!)
        builder.recyclerView.adapter = wrapperAdapter
        wrapperAdapter?.setRepeatListener(this)
        checkGridLayoutManager()
    }

    private fun checkGridLayoutManager() {
        if (builder.recyclerView.layoutManager is GridLayoutManager) {
            val item = DefaultGridLayoutItem(builder.recyclerView.layoutManager as GridLayoutManager)
            wrapperSpanSizeLookup = WrapperSpanSizeLookup(
                (builder.recyclerView.layoutManager as GridLayoutManager).spanSizeLookup, item, wrapperAdapter
            )
            (builder.recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = wrapperSpanSizeLookup
        }
    }

    private fun setupScrollListener() {
        builder.recyclerView.addOnScrollListener(scrollListener)
    }

    private fun checkAdapterState() {
        if (isCanLoadMore) {
            builder.onLoadMore.invoke()
        }
    }

    override fun onAdapterChange() {
        builder.recyclerView.post {
            val status = PaginateStatus.getStatus(isLoadedAllItems, isError)
            wrapperAdapter?.stateChanged(status)
            checkScroll()
        }
    }

    private fun checkScroll() {
        if (ScrollUtils.isOnBottom(builder.recyclerView, builder.loadingTriggerThreshold)) {
            checkAdapterState()
        }
    }

    /**
     * This method will show error on the bottom of your recyclerView.
     *
     * @param isShowError - true if show, false if hide
     */
    fun showError(isShowError: Boolean) {
        if (isShowError) {
            isError = true
            wrapperAdapter?.stateChanged(PaginateStatus.ERROR)
            ScrollUtils.fullScrollToBottom(builder.recyclerView, wrapperAdapter!!)
        } else {
            isError = false
        }
    }

    /**
     * This method will show error on the bottom of your recyclerView.
     *
     * @param show - true if show, false if hide
     */
    fun showLoading(show: Boolean) {
        if (show) {
            isLoading = true
            wrapperAdapter?.stateChanged(PaginateStatus.LOADING)
        } else {
            isLoading = false
        }
    }

    /**
     * This method show error on the bottom of your recyclerView.
     *
     * @param isNoMoreItems - true if items ended, false if no
     */
    fun setNoMoreItems(isNoMoreItems: Boolean) {
        if (isNoMoreItems) {
            this.isLoadedAllItems = true
            wrapperAdapter?.stateChanged(PaginateStatus.NO_MORE_ITEMS)
        } else {
            this.isLoadedAllItems = false
        }
    }

    /**
     * This method reset all setting of no paginate.
     */
    fun setResetItems() {
        this.isError = false
        this.isLoading = false
        this.isLoadedAllItems = false
    }

    override fun onClickRepeat() {
        showError(false)
        checkScroll()
    }

    /**
     * This method unsubscribe observer and change listeners reference to null
     * for avoid memory leaks.
     */
    fun unbind() {
        builder.recyclerView.removeOnScrollListener(scrollListener)
        when {
            builder.recyclerView.layoutManager is LinearLayoutManager -> {
                wrapperAdapter?.unbind()
                userAdapter.unregisterAdapterDataObserver(wrapperAdapterObserver!!)
                builder.recyclerView.adapter = userAdapter
            }
            builder.recyclerView.layoutManager is GridLayoutManager -> {
                if(wrapperSpanSizeLookup != null) {
                    val spanSizeLookup = wrapperSpanSizeLookup!!.wrappedSpanSizeLookup
                    (builder.recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = spanSizeLookup
                }
            }
            else -> {

            }
        }
    }
}

