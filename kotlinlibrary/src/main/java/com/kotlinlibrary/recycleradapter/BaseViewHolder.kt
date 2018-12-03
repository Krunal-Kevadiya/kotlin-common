package com.kotlinlibrary.recycleradapter

interface BaseViewHolder<ItemT : Any> {
    fun bind(item: ItemT)
    fun unbind() = Unit
}