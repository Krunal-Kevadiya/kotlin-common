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
            override fun bindView(item: Any) {
                adapterViewType.configuration.bindHolder(itemView, item)
            }
        }
        setUpClickListener(adapterViewType, holder)
        return holder
    }

    /*override fun onBindViewClick(holder: BaseViewHolder<Any>) {
        super.onBindViewClick(holder)
        setUpClickListener(holder)
    }*/

    private fun setUpClickListener(adapterViewType: AdapterViewType<Any>, holder: BaseViewHolder<Any>) {
        val itemView = holder.itemView
        if(adapterViewType.configuration.clickResId.isEmpty()) {
            itemView.setOnClickListener { view ->
                val adapterPosition = holder.adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    adapterViewType.configuration.clickListener.invoke(view.id, itemList[adapterPosition])
                }
            }
        } else {
            adapterViewType.configuration.clickResId.forEach { id ->
                itemView.findViewById<View>(id)?.setOnClickListener { view ->
                    val adapterPosition = holder.adapterPosition
                    if (adapterPosition != androidx.recyclerview.widget.RecyclerView.NO_POSITION) {
                        adapterViewType.configuration.clickListener.invoke(view.id, itemList[adapterPosition])
                    }
                }
            }
        }
    }

    override operator fun plusAssign(itemList: MutableList<Any>) {
        this.itemList.reset(itemList)
        notifyDataSetChanged()
    }

    override operator fun plus(itemList: List<Any>) {
        this.itemList.addAll(itemList).also(::dispatchUpdates)
    }

    override operator fun plus(item: Any) {
        itemList.add(item).also(::dispatchUpdates)
    }

    override fun add(index: Int, item: Any) {
        itemList.add(index, item).also(::dispatchUpdates)
    }

    override fun addAll(items: MutableList<Any>) {
        itemList.addAll(items).also(::dispatchUpdates)
    }

    override fun addAll(index: Int, items: MutableList<Any>) {
        itemList.addAll(index, items).also(::dispatchUpdates)
    }

    override operator fun set(index: Int, item: Any) {
        itemList.set(index, item).also(::dispatchUpdates)
    }

    override fun insert(index: Int, itemList: List<Any>) {
        this.itemList.addAll(index, itemList).also(::dispatchUpdates)
    }

    override operator fun minus(index: Int) {
        itemList.removeAt(index).also(::dispatchUpdates)
    }

    override operator fun minus(item: Any) {
        itemList.remove(item).also(::dispatchUpdates)
    }

    override fun clear() {
        itemList.clear().also(::dispatchUpdates)
    }

    private fun dispatchUpdates(diffUtilCallback: DiffUtilCallback<Any>) {
        with(diffUtilCallback) {
            contentComparator = multiTypedAdapterConfiguration.contentComparator
            itemsComparator = multiTypedAdapterConfiguration.itemsComparator
            DiffUtil.calculateDiff(this).dispatchUpdatesTo(this@MultiTypedAdapter)
        }
    }
}
