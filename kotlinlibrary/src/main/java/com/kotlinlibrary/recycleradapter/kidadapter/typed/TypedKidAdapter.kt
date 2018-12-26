package com.kotlinlibrary.recycleradapter.kidadapter.typed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.kidadapter.base.BaseAdapter
import com.kotlinlibrary.recycleradapter.kidadapter.base.BaseViewHolder

open class TypedKidAdapter(
    private val typedKidAdapterConfiguration: TypedKidAdapterConfiguration
) : BaseAdapter<Any, BaseViewHolder<Any>>(typedKidAdapterConfiguration.getAllItems()) {
    init {
        typedKidAdapterConfiguration.validate()
    }

    override fun getItemViewType(position: Int): Int {
        return typedKidAdapterConfiguration.viewTypes.first {
            it.viewType == typedKidAdapterConfiguration.getAllItems()[position]::class.hashCode()
        }.viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        val adapterViewType = typedKidAdapterConfiguration.viewTypes.first { it.viewType == viewType }
        val view = LayoutInflater.from(parent.context).inflate(adapterViewType.configuration.layoutResId, parent, false)
        val viewHolder = object : BaseViewHolder<Any>(view) {
            override fun bindView(item: Any) {
                adapterViewType.configuration.bindHolder(itemView, item)
            }
        }
        val itemView = viewHolder.itemView
        itemView.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClick(itemView, adapterPosition)
            }
        }
        return viewHolder
    }

    open fun onItemClick(itemView: View, position: Int) {}
}
