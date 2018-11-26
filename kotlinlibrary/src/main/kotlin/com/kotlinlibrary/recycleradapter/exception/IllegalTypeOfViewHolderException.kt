package com.kotlinlibrary.recycleradapter.exception

class IllegalTypeOfViewHolderException(holderClassName: String?)
    : Exception("$holderClassName does not implement SrvViewHolder")