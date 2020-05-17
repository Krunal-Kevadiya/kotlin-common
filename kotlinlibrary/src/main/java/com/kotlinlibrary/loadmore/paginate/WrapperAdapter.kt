package com.kotlinlibrary.loadmore.paginate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.loadmore.callback.OnRepeatListener
import com.kotlinlibrary.loadmore.item.ErrorItem
import com.kotlinlibrary.loadmore.item.LoadingItem

class WrapperAdapter internal constructor(
    private val userAdapter:  RecyclerView.Adapter<RecyclerView.ViewHolder>,
    private val loadingItem: LoadingItem,
    private val errorItem: ErrorItem
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var paginateStatus = PaginateStatus.NO_SET
    private var repeatListener: OnRepeatListener? = null

    private val errorOrLoadingItemPosition: Int
        get() = if (isErrorOrLoading) itemCount - 1 else -1

    private val isErrorOrLoading: Boolean
        get() = paginateStatus == PaginateStatus.LOADING || paginateStatus == PaginateStatus.ERROR

    override fun getItemViewType(position: Int): Int {
        return when {
            isLoadingItem(position) -> ITEM_VIEW_TYPE_LOADING
            isErrorItem(position) -> ITEM_VIEW_TYPE_ERROR
            else -> userAdapter.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_LOADING -> loadingItem.onCreateViewHolder(parent, viewType)
            ITEM_VIEW_TYPE_ERROR -> errorItem.onCreateViewHolder(parent, viewType)
            else -> userAdapter.onCreateViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            isLoadingItem(position) -> loadingItem.onBindViewHolder(holder, position)
            isErrorItem(position) -> errorItem.onBindViewHolder(holder, position, repeatListener)
            else -> userAdapter.onBindViewHolder(holder, position)
        }
    }

    fun isErrorItem(position: Int): Boolean {
        return paginateStatus == PaginateStatus.ERROR && position == errorOrLoadingItemPosition
    }

    fun isLoadingItem(position: Int): Boolean {
        return paginateStatus == PaginateStatus.LOADING && position == errorOrLoadingItemPosition
    }

    override fun getItemCount(): Int {
        return if (isErrorOrLoading) userAdapter.itemCount + 1 else userAdapter.itemCount
    }

    internal fun stateChanged(status: PaginateStatus) {
        if (this.paginateStatus != status) {
            this.paginateStatus = status
            notifyDataSetChanged()
        }
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
        userAdapter.setHasStableIds(hasStableIds)
    }

    internal fun setRepeatListener(repeatListener: OnRepeatListener) {
        this.repeatListener = repeatListener
    }

    internal fun unbind() {
        repeatListener = null
    }

    companion object {
        private const val ITEM_VIEW_TYPE_LOADING = 46699933
        private const val ITEM_VIEW_TYPE_ERROR = 46699932
    }
}
