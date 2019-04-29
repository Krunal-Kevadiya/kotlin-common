package com.kotlinlibrary.recycleradapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.simple.SingleAdapter
import com.kotlinlibrary.recycleradapter.simple.SingleAdapterConfiguration
import com.kotlinlibrary.recycleradapter.simple.SingleBindingAdapter
import com.kotlinlibrary.recycleradapter.typed.MultiTypedAdapter
import com.kotlinlibrary.recycleradapter.typed.MultiTypedAdapterConfiguration
import com.kotlinlibrary.recycleradapter.typed.MultiTypedBindingAdapter

/**
 * Simple way to create [RecyclerView.Adapter] with no view types
 * @param block hare adapter can be configured
 * @param T adapter item models type.
 * @receiver any instance of [RecyclerView]
 * @return a child instance of a [RecyclerView.Adapter]
 */
fun <T> RecyclerView.setUp(block: SingleAdapterConfiguration<T>.() -> Unit): SingleAdapter<T> {
    val configuration = SingleAdapterConfiguration<T>().apply(block)
    return SingleAdapter(configuration).apply {
        layoutManager = configuration.layoutManager ?: LinearLayoutManager(context)
        adapter = this
    }
}

/**
 * Simple way to create [RecyclerView.Adapter] with no view types
 * @param block hare adapter can be configured
 * @param T adapter item models type.
 * @receiver any instance of [RecyclerView]
 * @return a child instance of a [RecyclerView.Adapter]
 */
fun <T> RecyclerView.setUpBinding(block: SingleAdapterConfiguration<T>.() -> Unit): SingleBindingAdapter<T> {
    val configuration = SingleAdapterConfiguration<T>().apply(block)
    return SingleBindingAdapter(configuration).apply {
        layoutManager = configuration.layoutManager ?: LinearLayoutManager(context)
        adapter = this
    }
}

/**
 * Simple way to create [RecyclerView.Adapter] with view types.
 * @param block hare adapter can be configured
 * @receiver any instance of [RecyclerView]
 * @return a child instance of a [RecyclerView.Adapter]
 */
fun RecyclerView.setUp(block: MultiTypedAdapterConfiguration.() -> Unit): MultiTypedAdapter {
    val adapterDsl = MultiTypedAdapterConfiguration().apply(block)
    return MultiTypedAdapter(adapterDsl).apply {
        layoutManager = adapterDsl.layoutManager ?: LinearLayoutManager(context)
        adapter = this
    }
}

/**
 * Simple way to create [RecyclerView.Adapter] with view types.
 * @param block hare adapter can be configured
 * @receiver any instance of [RecyclerView]
 * @return a child instance of a [RecyclerView.Adapter]
 */
fun RecyclerView.setUpBinding(block: MultiTypedAdapterConfiguration.() -> Unit): MultiTypedBindingAdapter {
    val adapterDsl = MultiTypedAdapterConfiguration().apply(block)
    return MultiTypedBindingAdapter(adapterDsl).apply {
        layoutManager = adapterDsl.layoutManager ?: LinearLayoutManager(context)
        adapter = this
    }
}