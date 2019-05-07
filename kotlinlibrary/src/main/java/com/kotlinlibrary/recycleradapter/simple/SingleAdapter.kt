package com.kotlinlibrary.recycleradapter.simple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.base.BaseAdapter
import com.kotlinlibrary.recycleradapter.base.BaseViewHolder
import com.kotlinlibrary.recycleradapter.base.DiffUtilCallback

open class SingleAdapter<T> (
    private val configuration: SingleAdapterConfiguration<T>
) : BaseAdapter<T, BaseViewHolder<T>>(configuration.items) {
    init {
        configuration.validate()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(configuration.layoutResId, parent, false)
        val holder = object : BaseViewHolder<T>(view) {
            override fun bindView(position: Int, item: T) {
                configuration.bindHolder(itemView, position, item)
            }
        }
        setUpClickListener(holder)
        return holder
    }

    private fun setUpClickListener(holder: BaseViewHolder<T>) {
        val itemView = holder.itemView
        if(configuration.clickResId.isEmpty()) {
            itemView.setOnClickListener { view ->
                val adapterPosition = holder.adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    configuration.clickListener.invoke(view.id, adapterPosition, itemList[adapterPosition])
                }
            }
        } else {
            configuration.clickResId.forEach { id ->
                itemView.findViewById<View>(id)?.setOnClickListener { view ->
                    val adapterPosition = holder.adapterPosition
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        configuration.clickListener.invoke(view.id, adapterPosition, itemList[adapterPosition])
                    }
                }
            }
        }
    }

    override operator fun plusAssign(itemList: MutableList<T>) {
        this.itemList.reset(itemList)
        notifyDataSetChanged()
    }

    override operator fun plus(itemList: List<T>): BaseAdapter<T, BaseViewHolder<T>> {
        if(configuration.isDiffUtils) {
            this.itemList.addAll(itemList).also(::dispatchUpdates)
        } else {
            val size = this.itemList.size + 1
            this.itemList.addAll(itemList)
            notifyItemRangeInserted(size, itemList.size)
        }
        return this
    }

    override operator fun plus(item: T): BaseAdapter<T, BaseViewHolder<T>> {
        if(configuration.isDiffUtils) {
            itemList.add(item).also(::dispatchUpdates)
        } else {
            itemList.add(item)
            notifyItemInserted(itemList.size - 1)
        }
        return this
    }

    override fun add(index: Int, item: T): BaseAdapter<T, BaseViewHolder<T>> {
        if(configuration.isDiffUtils) {
            itemList.add(index, item).also(::dispatchUpdates)
        } else {
            itemList.add(index, item)
            notifyItemInserted(index)
        }
        return this
    }

    override fun addAll(items: MutableList<T>): BaseAdapter<T, BaseViewHolder<T>> {
        if(configuration.isDiffUtils) {
            itemList.addAll(items).also(::dispatchUpdates)
        } else {
            val size = itemList.size + 1
            itemList.addAll(items)
            notifyItemRangeInserted(size, items.size)
        }
        return this
    }

    override fun addAll(index: Int, items: MutableList<T>): BaseAdapter<T, BaseViewHolder<T>> {
        if(configuration.isDiffUtils) {
            itemList.addAll(index, items).also(::dispatchUpdates)
        } else {
            val size = itemList.size - index
            itemList.addAll(items)
            notifyItemRangeInserted(index, items.size + size)
        }
        return this
    }

    override operator fun set(index: Int, item: T) {
        if(configuration.isDiffUtils) {
            itemList.set(index, item).also(::dispatchUpdates)
        } else {
            itemList.set(index, item)
            notifyItemChanged(index)
        }
    }

    override fun insert(index: Int, itemList: List<T>): BaseAdapter<T, BaseViewHolder<T>> {
        if(configuration.isDiffUtils) {
            this.itemList.addAll(index, itemList).also(::dispatchUpdates)
        } else {
            val size = this.itemList.size - index
            this.itemList.addAll(itemList)
            notifyItemRangeInserted(index, itemList.size + size)
        }
        return this
    }

    override fun remove(index: Int): BaseAdapter<T, BaseViewHolder<T>> {
        if(configuration.isDiffUtils) {
            itemList.removeAt(index).also(::dispatchUpdates)
        } else {
            itemList.removeAt(index)
            notifyItemRemoved(index)
        }
        return this
    }

    override operator fun minus(item: T): BaseAdapter<T, BaseViewHolder<T>> {
        if(configuration.isDiffUtils) {
            itemList.remove(item).also(::dispatchUpdates)
        } else {
            val index = itemList.indexOf(item)
            itemList.remove(item)
            notifyItemRemoved(index)
        }
        return this
    }

    override fun clear(): BaseAdapter<T, BaseViewHolder<T>> {
        if(configuration.isDiffUtils) {
            itemList.clear().also(::dispatchUpdates)
        } else {
            itemList.clear()
            notifyDataSetChanged()
        }
        return this
    }

    private fun dispatchUpdates(diffUtilCallback: DiffUtilCallback<T>) {
        with(diffUtilCallback) {
            contentComparator = configuration.contentComparator
            itemsComparator = configuration.itemsComparator
            DiffUtil.calculateDiff(this).dispatchUpdatesTo(this@SingleAdapter)
        }
    }
}