package com.kotlinlibrary.recycleradapter.simple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.base.DataBindingBaseAdapter
import com.kotlinlibrary.recycleradapter.base.DataBindingBaseViewHolder
import com.kotlinlibrary.recycleradapter.base.DiffUtilCallback

open class SingleBindingAdapter<T> (
    private val configuration: SingleAdapterConfiguration<T>
) : DataBindingBaseAdapter<T, DataBindingBaseViewHolder<T>>(configuration.items) {
    init {
        configuration.validate()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingBaseViewHolder<T> {
        val view: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), configuration.layoutResId, parent, false
        )
        val holder = object : DataBindingBaseViewHolder<T>(view) {
            override fun bindView(position: Int, item: T) {
                if(configuration.layoutBr != -1) {
                    view.setVariable(configuration.layoutBr, item)
                    view.executePendingBindings()
                }
                configuration.bindBindingHolder(view, position, item)
            }
        }
        setUpClickListener(holder)
        return holder
    }

    private fun setUpClickListener(holder: DataBindingBaseViewHolder<T>) {
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
                itemView.rootView.findViewById<View>(id).setOnClickListener { view ->
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

    override operator fun plus(itemList: List<T>) {
        this.itemList.addAll(itemList).also(::dispatchUpdates)
    }

    override operator fun plus(item: T) {
        itemList.add(item).also(::dispatchUpdates)
    }

    override fun add(index: Int, item: T) {
        itemList.add(index, item).also(::dispatchUpdates)
    }

    override fun addAll(items: MutableList<T>) {
        itemList.addAll(items).also(::dispatchUpdates)
    }

    override fun addAll(index: Int, items: MutableList<T>) {
        itemList.addAll(index, items).also(::dispatchUpdates)
    }

    override operator fun set(index: Int, item: T) {
        itemList.set(index, item).also(::dispatchUpdates)
    }

    override fun insert(index: Int, itemList: List<T>) {
        this.itemList.addAll(index, itemList).also(::dispatchUpdates)
    }

    override operator fun minus(index: Int) {
        itemList.removeAt(index).also(::dispatchUpdates)
    }

    override operator fun minus(item: T) {
        itemList.remove(item).also(::dispatchUpdates)
    }

    override fun clear() {
        itemList.clear().also(::dispatchUpdates)
    }

    private fun dispatchUpdates(diffUtilCallback: DiffUtilCallback<T>) {
        with(diffUtilCallback) {
            contentComparator = configuration.contentComparator
            itemsComparator = configuration.itemsComparator
            DiffUtil.calculateDiff(this).dispatchUpdatesTo(this@SingleBindingAdapter)
        }
    }
}