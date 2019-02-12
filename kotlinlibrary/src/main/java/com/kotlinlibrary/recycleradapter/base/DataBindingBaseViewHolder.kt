package com.kotlinlibrary.recycleradapter.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DataBindingBaseViewHolder<T>(itemView: ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {
    abstract fun bindView(position:Int, item: T)
}