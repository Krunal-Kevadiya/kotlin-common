package com.kotlinlibrary.skeletonlayout

import android.view.View
import androidx.recyclerview.widget.RecyclerView

object Skeleton {

    fun bind(recyclerView: RecyclerView): RecyclerViewSkeletonScreen.Builder {
        return RecyclerViewSkeletonScreen.Builder(recyclerView)
    }

    fun bind(view: View): ViewSkeletonScreen.Builder {
        return ViewSkeletonScreen.Builder(view)
    }

}
