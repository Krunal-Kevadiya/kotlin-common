package com.kotlinlibrary.loadmore

enum class Status(var title: String?) {
    Idle(null),
    Error("error..."),
    Loading("loading..."),
    NoMore("noMore...");
}

