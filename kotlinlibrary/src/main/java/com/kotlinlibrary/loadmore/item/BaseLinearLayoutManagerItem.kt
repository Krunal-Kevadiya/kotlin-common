package com.kotlinlibrary.loadmore.item

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface BaseLinearLayoutManagerItem {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
}
