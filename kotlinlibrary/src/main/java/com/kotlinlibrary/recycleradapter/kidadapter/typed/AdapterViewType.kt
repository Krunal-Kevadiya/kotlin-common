package com.kotlinlibrary.recycleradapter.kidadapter.typed

class AdapterViewType<T>(block: AdapterViewTypeConfiguration.() -> Unit) {
    internal val configuration = AdapterViewTypeConfiguration().apply(block)
    val viewType: Int = this.hashCode()
}