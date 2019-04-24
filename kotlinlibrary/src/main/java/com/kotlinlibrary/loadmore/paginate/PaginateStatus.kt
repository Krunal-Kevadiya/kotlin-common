package com.kotlinlibrary.loadmore.paginate

internal enum class PaginateStatus {
    NO_MORE_ITEMS, LOADING, ERROR;

    companion object {
        fun getStatus(loadedAllItems: Boolean, adapterError: Boolean): PaginateStatus {
            return when {
                loadedAllItems -> NO_MORE_ITEMS
                adapterError -> ERROR
                else -> LOADING
            }
        }
    }
}
