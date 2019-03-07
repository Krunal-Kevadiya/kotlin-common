package com.kotlinlibrary.loadmore

enum class Status(var title: String) {
    Idle(title = Status.STR_IDLE),
    Error(title = Status.STR_ERROR),
    Loading(title = Status.STR_LOADING),
    NoMore(title = Status.STR_NO_MORE);

    companion object {
        const val STR_IDLE: String = ""
        const val STR_ERROR: String = "error..."
        const val STR_LOADING: String = "loading..."
        const val STR_NO_MORE: String = "noMore..."
    }
}

