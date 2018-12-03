package com.kotlinlibrary.loadmore

import android.content.Context
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.R
import kotlinx.android.synthetic.main.listitem_loadmore.view.*

class LoadMoreWrapper(
    val context: Context,
    val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    var status: Status = Status.Idle,
    var customView: ((LinearLayout, TextView) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_LOAD_MORE = Int.MAX_VALUE
    }

    override fun getItemCount(): Int {
        return when (status) {
            Status.Loading, Status.Error, Status.NoMore -> {
                adapter.itemCount + 1
            }
            else -> {
                adapter.itemCount
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            isLoadingType(position) || isErrorType(position) || isNoMoreType(position) -> {
                TYPE_LOAD_MORE
            }
            else -> {
                adapter.getItemViewType(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LOAD_MORE -> {
                LoadMoreVH(inflate(parent, R.layout.listitem_loadmore))
            }
            else -> {
                adapter.onCreateViewHolder(parent, viewType)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (getItemViewType(position)) {
            TYPE_LOAD_MORE -> {
                if (holder.itemView.layoutParams is StaggeredGridLayoutManager.LayoutParams) {
                    val layoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
                    layoutParams.isFullSpan = true
                }
                (holder as LoadMoreVH).onBindViewHolder(status, customView)
            }
            else -> {
                adapter.onBindViewHolder(holder, position)
            }
        }
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
        adapter.setHasStableIds(hasStableIds)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        adapter.onViewRecycled(holder)
    }

    fun notifyStatusChanged(newStatus: Status) {
        if (status != newStatus) {
            status = newStatus
            notifyDataSetChanged()
        }
    }

    class LoadMoreVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindViewHolder(status: Status, customView: ((LinearLayout, TextView) -> Unit)?) {
            itemView.tvTitle.apply {
                text = status.title ?: ""
            }
            customView?.invoke(itemView.linearLayout, itemView.tvTitle)
        }
    }
}