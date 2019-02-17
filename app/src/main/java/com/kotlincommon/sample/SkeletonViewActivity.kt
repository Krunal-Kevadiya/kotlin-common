package com.kotlincommon.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlinlibrary.recycleradapter.setUp
import com.kotlinlibrary.skeletonlayout.Skeleton
import com.kotlinlibrary.skeletonlayout.SkeletonScreen
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs
import kotlinx.android.synthetic.main.activity_recycler_adapter.recyclerView
import kotlinx.android.synthetic.main.item_advertisement.view.textViewAdvertisement

class SkeletonViewActivity : AppCompatActivity() {
    private lateinit var skeleton: SkeletonScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_adapter)
        val adapter = recyclerView.setUp<String> {
            withLayoutResId(R.layout.item_advertisement)
            onBind { _, item ->
                textViewAdvertisement.text = item
            }
            onClick { id, index, item ->
                logs("(${R.id.textViewAdvertisement}, $id) -> $item", LogType.ERROR)
            }
            withItems(mutableListOf("one", "two", "three", "four", "five", "six", "seven"))
        }
        skeleton = Skeleton.bind(recyclerView)
            .adapter(adapter)
            .shimmer(true)
            .angle(20)
            .frozen(false)
            .duration(1200)
            .count(10)
            .load(R.layout.item_skeleton)
            .show()
        recyclerView.postDelayed({
            skeleton.hide()
        }, 3000)
    }
}