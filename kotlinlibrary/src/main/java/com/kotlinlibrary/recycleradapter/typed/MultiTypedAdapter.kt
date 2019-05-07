package com.kotlinlibrary.recycleradapter.typed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.base.BaseAdapter
import com.kotlinlibrary.recycleradapter.base.BaseViewHolder
import com.kotlinlibrary.recycleradapter.base.DiffUtilCallback

open class MultiTypedAdapter(
    private val multiTypedAdapterConfiguration: MultiTypedAdapterConfiguration
) : BaseAdapter<Any, BaseViewHolder<Any>>(multiTypedAdapterConfiguration.items) {
    init {
        multiTypedAdapterConfiguration.validate()
    }

    override fun getItemViewType(position: Int): Int {
        return multiTypedAdapterConfiguration.viewTypes.first {
            it.viewType == multiTypedAdapterConfiguration.items[position]::class.hashCode()
        }.viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        val adapterViewType = multiTypedAdapterConfiguration.viewTypes.first { it.viewType == viewType }
        val view = LayoutInflater.from(parent.context).inflate(adapterViewType.configuration.layoutResId, parent, false)
        val holder = object : BaseViewHolder<Any>(view) {
            override fun bindView(position: Int, item: Any) {
                adapterViewType.configuration.bindHolder(itemView, position, item)
            }
        }
        setUpClickListener(adapterViewType, holder)
        return holder
    }

    private fun setUpClickListener(adapterViewType: AdapterViewType<Any>, holder: BaseViewHolder<Any>) {
        val itemView = holder.itemView
        if(adapterViewType.configuration.clickResId.isEmpty()) {
            itemView.setOnClickListener { view ->
                val adapterPosition = holder.adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    adapterViewType.configuration.clickListener.invoke(view.id, adapterPosition, itemList[adapterPosition])
                }
            }
        } else {
            adapterViewType.configuration.clickResId.forEach { id ->
                itemView.findViewById<View>(id)?.setOnClickListener { view ->
                    val adapterPosition = holder.adapterPosition
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        adapterViewType.configuration.clickListener.invoke(view.id, adapterPosition, itemList[adapterPosition])
                    }
                }
            }
        }
    }

    override operator fun plusAssign(itemList: MutableList<Any>) {
        this.itemList.reset(itemList)
        notifyDataSetChanged()
    }

    override operator fun plus(itemList: List<Any>): BaseAdapter<Any, BaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            this.itemList.addAll(itemList).also(::dispatchUpdates)
        } else {
            val size = this.itemList.size + 1
            this.itemList.addAll(itemList)
            notifyItemRangeInserted(size, itemList.size)
        }
        return this
    }

    override operator fun plus(item: Any): BaseAdapter<Any, BaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.add(item).also(::dispatchUpdates)
        } else {
            itemList.add(item)
            notifyItemInserted(itemList.size - 1)
        }
        return this
    }

    override fun add(index: Int, item: Any): BaseAdapter<Any, BaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.add(index, item).also(::dispatchUpdates)
        } else {
            itemList.add(index, item)
            notifyItemInserted(index)
        }
        return this
    }

    override fun addAll(items: MutableList<Any>): BaseAdapter<Any, BaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.addAll(items).also(::dispatchUpdates)
        } else {
            val size = itemList.size + 1
            itemList.addAll(items)
            notifyItemRangeInserted(size, items.size)
        }
        return this
    }

    override fun addAll(index: Int, items: MutableList<Any>): BaseAdapter<Any, BaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.addAll(index, items).also(::dispatchUpdates)
        } else {
            val size = itemList.size - index
            itemList.addAll(items)
            notifyItemRangeInserted(index, items.size + size)
        }
        return this
    }

    override operator fun set(index: Int, item: Any) {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.set(index, item).also(::dispatchUpdates)
        } else {
            itemList.set(index, item)
            notifyItemChanged(index)
        }
    }

    override fun insert(index: Int, itemList: List<Any>): BaseAdapter<Any, BaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            this.itemList.addAll(index, itemList).also(::dispatchUpdates)
        } else {
            val size = this.itemList.size - index
            this.itemList.addAll(itemList)
            notifyItemRangeInserted(index, itemList.size + size)
        }
        return this
    }

    override fun remove(index: Int): BaseAdapter<Any, BaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.removeAt(index).also(::dispatchUpdates)
        } else {
            itemList.removeAt(index)
            notifyItemRemoved(index)
        }
        return this
    }

    override operator fun minus(item: Any): BaseAdapter<Any, BaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.remove(item).also(::dispatchUpdates)
        } else {
            val index = itemList.indexOf(item)
            itemList.remove(item)
            notifyItemRemoved(index)
        }
        return this
    }

    override fun clear(): BaseAdapter<Any, BaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.clear().also(::dispatchUpdates)
        } else {
            itemList.clear()
            notifyDataSetChanged()
        }
        return this
    }

    private fun dispatchUpdates(diffUtilCallback: DiffUtilCallback<Any>) {
        with(diffUtilCallback) {
            contentComparator = multiTypedAdapterConfiguration.contentComparator
            itemsComparator = multiTypedAdapterConfiguration.itemsComparator
            DiffUtil.calculateDiff(this).dispatchUpdatesTo(this@MultiTypedAdapter)
        }
    }
}
