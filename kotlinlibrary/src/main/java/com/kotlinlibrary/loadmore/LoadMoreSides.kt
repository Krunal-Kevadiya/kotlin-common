package com.kotlinlibrary.loadmore

import androidx.annotation.IntDef
@Retention(AnnotationRetention.SOURCE)
@IntDef(LoadMoreSides.UP_SIDE, LoadMoreSides.DOWN_SIDE, LoadMoreSides.BOTH_SIDE)
annotation class LoadMoreSide

class LoadMoreSides {
    companion object{
        const val UP_SIDE = 0
        const val DOWN_SIDE = 1
        const val BOTH_SIDE = 2
    }
}

