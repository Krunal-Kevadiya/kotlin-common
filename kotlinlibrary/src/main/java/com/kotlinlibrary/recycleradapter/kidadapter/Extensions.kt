package com.kotlinlibrary.recycleradapter.kidadapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.kidadapter.simple.SingleKidAdapter
import com.kotlinlibrary.recycleradapter.kidadapter.simple.SingleKidAdapterConfiguration
import com.kotlinlibrary.recycleradapter.kidadapter.typed.TypedKidAdapter
import com.kotlinlibrary.recycleradapter.kidadapter.typed.TypedKidAdapterConfiguration

fun <T> RecyclerView.setUp(block: SingleKidAdapterConfiguration<T>.() -> Unit): SingleKidAdapter<T> {
    val configuration = SingleKidAdapterConfiguration<T>().apply(block)
    return SingleKidAdapter(configuration).apply {
        layoutManager = configuration.layoutManager ?: LinearLayoutManager(context)
        adapter = this
    }
}

fun RecyclerView.setUp(block: TypedKidAdapterConfiguration.() -> Unit): TypedKidAdapter {
    val adapterDsl = TypedKidAdapterConfiguration().apply(block)
    return TypedKidAdapter(adapterDsl).apply {
        layoutManager = adapterDsl.layoutManager ?: LinearLayoutManager(context)
        adapter = this
    }
}