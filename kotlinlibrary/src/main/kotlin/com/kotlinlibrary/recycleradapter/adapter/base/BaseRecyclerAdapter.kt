package com.kotlinlibrary.recycleradapter.adapter.base

import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.BaseViewHolder
import com.kotlinlibrary.recycleradapter.exception.IllegalTypeOfViewHolderException

abstract class BaseRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected open var displayedItems = emptyList<Any>()

    @Suppress("UNCHECKED_CAST")
    final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            (holder as? BaseViewHolder<Any>)?.bind(displayedItems[position])
                    ?: throw IllegalTypeOfViewHolderException(holder.javaClass.simpleName)

    final override fun getItemCount() = displayedItems.size

    final override fun getItemViewType(position: Int) = displayedItems[position]::class.hashCode()

    fun getItemClassName(viewType: Int) =
            displayedItems.find { it::class.hashCode() == viewType }?.javaClass?.simpleName
}