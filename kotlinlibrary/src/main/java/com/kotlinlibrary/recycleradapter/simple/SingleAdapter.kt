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

    override operator fun plusAssign(items: MutableList<T>) {
        itemList.reset(items)
        notifyDataSetChanged()
    }

    override operator fun plus(items: List<T>) {
        if(configuration.isDiffUtils) {
            itemList.addAll(items).also(::dispatchUpdates)
        } else {
            val size = itemList.size + 1
            itemList.addAll(items)
            notifyItemRangeInserted(size, items.size)
        }
    }

    override operator fun plus(item: T) {
        if(configuration.isDiffUtils) {
            itemList.add(item).also(::dispatchUpdates)
        } else {
            itemList.add(item)
            notifyItemInserted(itemList.size - 1)
        }
    }

    override fun add(index: Int, item: T) {
        if(configuration.isDiffUtils) {
            itemList.add(index, item).also(::dispatchUpdates)
        } else {
            itemList.add(index, item)
            notifyItemInserted(index)
        }
    }

    override fun addAll(items: MutableList<T>) {
        if(configuration.isDiffUtils) {
            itemList.addAll(items).also(::dispatchUpdates)
        } else {
            val size = itemList.size + 1
            itemList.addAll(items)
            notifyItemRangeInserted(size, items.size)
        }
    }

    override fun addAll(index: Int, items: MutableList<T>) {
        if(configuration.isDiffUtils) {
            itemList.addAll(index, items).also(::dispatchUpdates)
        } else {
            val size = itemList.size - index
            itemList.addAll(items)
            notifyItemRangeInserted(index, items.size + size)
        }
    }

    override operator fun set(index: Int, item: T) {
        if(configuration.isDiffUtils) {
            itemList.set(index, item).also(::dispatchUpdates)
        } else {
            itemList.set(index, item)
            notifyItemChanged(index)
        }
    }

    override fun insert(index: Int, items: List<T>) {
        if(configuration.isDiffUtils) {
            this.itemList.addAll(index, items).also(::dispatchUpdates)
        } else {
            val size = itemList.size - index
            itemList.addAll(items)
            notifyItemRangeInserted(index, items.size + size)
        }
    }

    override operator fun minus(index: Int) {
        if(configuration.isDiffUtils) {
            itemList.removeAt(index).also(::dispatchUpdates)
        } else {
            itemList.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    override operator fun minus(item: T) {
        if(configuration.isDiffUtils) {
            itemList.remove(item).also(::dispatchUpdates)
        } else {
            val index = itemList.indexOf(item)
            itemList.remove(item)
            notifyItemRemoved(index)
        }
    }

    override fun clear() {
        if(configuration.isDiffUtils) {
            itemList.clear().also(::dispatchUpdates)
        } else {
            itemList.clear()
            notifyDataSetChanged()
        }
    }

    private fun dispatchUpdates(diffUtilCallback: DiffUtilCallback<T>) {
        with(diffUtilCallback) {
            contentComparator = configuration.contentComparator
            itemsComparator = configuration.itemsComparator
            DiffUtil.calculateDiff(this).dispatchUpdatesTo(this@SingleAdapter)
        }
    }
}