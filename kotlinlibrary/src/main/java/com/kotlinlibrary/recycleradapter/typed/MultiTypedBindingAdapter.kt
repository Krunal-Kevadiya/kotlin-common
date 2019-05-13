package com.kotlinlibrary.recycleradapter.typed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.base.*

open class MultiTypedBindingAdapter(
    private val multiTypedAdapterConfiguration: MultiTypedAdapterConfiguration
) : DataBindingBaseAdapter<Any, DataBindingBaseViewHolder<Any>>(multiTypedAdapterConfiguration.items) {
    init {
        multiTypedAdapterConfiguration.validate()
    }

    override fun getItemViewType(position: Int): Int {
        return multiTypedAdapterConfiguration.viewTypes.first {
            it.viewType == multiTypedAdapterConfiguration.items[position]::class.hashCode()
        }.viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingBaseViewHolder<Any> {
        val adapterViewType = multiTypedAdapterConfiguration.viewTypes.first { it.viewType == viewType }
        val view: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            adapterViewType.configuration.layoutResId,
            parent,
            false
        )
        val holder = object : DataBindingBaseViewHolder<Any>(view) {
            override fun bindView(position: Int, item: Any) {
                if(adapterViewType.configuration.layoutBr != -1) {
                    view.setVariable(adapterViewType.configuration.layoutBr, item)
                    view.executePendingBindings()
                }
                adapterViewType.configuration.bindBindingHolder(view, position, item)
            }
        }
        setUpClickListener(adapterViewType, holder)
        return holder
    }

    private fun setUpClickListener(adapterViewType: AdapterViewType<Any>, holder: DataBindingBaseViewHolder<Any>) {
        val itemView = holder.itemView
        if(adapterViewType.configuration.clickResId.isEmpty()) {
            itemView.setOnClickListener { view ->
                val adapterPosition = holder.adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    adapterViewType.configuration.clickListener.invoke(view, adapterPosition, itemList[adapterPosition])
                }
            }
        } else {
            adapterViewType.configuration.clickResId.forEach { id ->
                itemView.findViewById<View>(id)?.setOnClickListener { view ->
                    val adapterPosition = holder.adapterPosition
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        adapterViewType.configuration.clickListener.invoke(view, adapterPosition, itemList[adapterPosition])
                    }
                }
            }
        }
    }

    override operator fun plusAssign(itemList: MutableList<Any>) {
        this.itemList.reset(itemList)
        notifyDataSetChanged()
    }

    override operator fun plus(itemList: List<Any>): DataBindingBaseAdapter<Any, DataBindingBaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            this.itemList.addAll(itemList).also(::dispatchUpdates)
        } else {
            val size = this.itemList.size + 1
            this.itemList.addAll(itemList)
            notifyItemRangeInserted(size, itemList.size)
        }
        return this
    }

    override operator fun plus(item: Any): DataBindingBaseAdapter<Any, DataBindingBaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.add(item).also(::dispatchUpdates)
        } else {
            itemList.add(item)
            notifyItemInserted(itemList.size - 1)
        }
        return this
    }

    override fun add(index: Int, item: Any): DataBindingBaseAdapter<Any, DataBindingBaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.add(index, item).also(::dispatchUpdates)
        } else {
            itemList.add(index, item)
            notifyItemInserted(index)
        }
        return this
    }

    override fun addAll(items: MutableList<Any>): DataBindingBaseAdapter<Any, DataBindingBaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.addAll(items).also(::dispatchUpdates)
        } else {
            val size = itemList.size + 1
            itemList.addAll(items)
            notifyItemRangeInserted(size, items.size)
        }
        return this
    }

    override fun addAll(index: Int, items: MutableList<Any>): DataBindingBaseAdapter<Any, DataBindingBaseViewHolder<Any>> {
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

    override fun insert(index: Int, itemList: List<Any>): DataBindingBaseAdapter<Any, DataBindingBaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            this.itemList.addAll(index, itemList).also(::dispatchUpdates)
        } else {
            val size = this.itemList.size - index
            this.itemList.addAll(itemList)
            notifyItemRangeInserted(index, itemList.size + size)
        }
        return this
    }

    override fun remove(index: Int): DataBindingBaseAdapter<Any, DataBindingBaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.removeAt(index).also(::dispatchUpdates)
        } else {
            itemList.removeAt(index)
            notifyItemRemoved(index)
        }
        return this
    }

    override operator fun minus(item: Any): DataBindingBaseAdapter<Any, DataBindingBaseViewHolder<Any>> {
        if(multiTypedAdapterConfiguration.isDiffUtils) {
            itemList.remove(item).also(::dispatchUpdates)
        } else {
            val index = itemList.indexOf(item)
            itemList.remove(item)
            notifyItemRemoved(index)
        }
        return this
    }

    override fun clear(): DataBindingBaseAdapter<Any, DataBindingBaseViewHolder<Any>> {
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
            DiffUtil.calculateDiff(this).dispatchUpdatesTo(this@MultiTypedBindingAdapter)
        }
    }
}
