package com.kotlinlibrary.recycleradapter.kidadapter.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, H : BaseViewHolder<T>>(protected var itemList: KidList<T>) : RecyclerView.Adapter<H>() {
    constructor(itemList: MutableList<T>) : this(KidList(itemList))

    override fun getItemCount() = itemList.size

    final override fun onBindViewHolder(holder: H, position: Int) {
        holder.bindView(itemList[position])
    }

    open operator fun plusAssign(itemList: MutableList<T>) {
        this.itemList.reset(itemList)
    }

    open operator fun plus(itemList: List<T>) {
        this.itemList.addAll(itemList)
        notifyItemRangeInserted(this.itemList.lastIndex, itemList.size)
    }

    open operator fun plus(item: T) {
        itemList.add(item)
        notifyItemInserted(itemList.lastIndex)
    }

    open fun add(index: Int, item: T) {
        itemList.add(index, item)
        notifyItemInserted(index)
    }

    open fun addAll(items: MutableList<T>) {
        val startPosition = itemList.size
        itemList.addAll(items)
        notifyItemRangeInserted(startPosition, items.size)
    }

    open fun addAll(index: Int, items: MutableList<T>) {
        itemList.addAll(index, items)
        notifyItemRangeInserted(index, items.size)
    }

    open operator fun set(index: Int, item: T) {
        itemList.set(index, item)
        notifyItemInserted(index)
    }

    open fun insert(index: Int, itemList: List<T>) {
        this.itemList.addAll(index, itemList)
        notifyItemRangeChanged(index, itemList.size)
    }

    operator fun get(index: Int): T {
        return itemList[index]
    }

    open operator fun minus(index: Int) {
        itemList.removeAt(index)
        notifyItemRemoved(index)
    }

    open operator fun minus(item: T) {
        val indexOfRemovedItem = itemList.indexOf(item)
        itemList.remove(item)
        notifyItemRemoved(indexOfRemovedItem)
    }

    open fun clear() {
        itemList.clear()
        notifyDataSetChanged()
    }
}