package com.kotlinlibrary.loadmore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun LoadMoreWrapper.isLoadingType(position: Int, @LoadMoreSide loadMoreSides: Int): Boolean {
    return when (loadMoreSides) {
        LoadMoreSides.DOWN_SIDE -> this.status == Status.Loading && position == adapter.itemCount
        LoadMoreSides.UP_SIDE -> this.status == Status.Loading && position == 0
        else -> false
    }
}

fun LoadMoreWrapper.isNoMoreType(position: Int, @LoadMoreSide loadMoreSides: Int): Boolean {
    return when (loadMoreSides) {
        LoadMoreSides.DOWN_SIDE -> this.status == Status.NoMore && position == adapter.itemCount
        LoadMoreSides.UP_SIDE -> this.status == Status.NoMore && position == 0
        else -> false
    }
}

fun LoadMoreWrapper.isErrorType(position: Int, @LoadMoreSide loadMoreSides: Int): Boolean {
    return when (loadMoreSides) {
        LoadMoreSides.DOWN_SIDE -> this.status == Status.Error && position == adapter.itemCount
        LoadMoreSides.UP_SIDE -> this.status == Status.Error && position == 0
        else -> false
    }
}

fun LoadMoreWrapper.inflate(parent: ViewGroup, layoutResId: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutResId, parent, false)
}