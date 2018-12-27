package com.kotlincommon.temp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.kotlincommon.sample.R
import com.kotlinlibrary.recycleradapter.dsladapter.setUp
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs
import kotlinx.android.synthetic.main.activity_recycler_adapter.recyclerView
import kotlinx.android.synthetic.main.item_advertisement.view.*
import kotlinx.android.synthetic.main.item_loadmore.view.*

class MultiTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_adapter)

        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val adapter = recyclerView.setUp {

            withViewType<String> {
                withLayoutResId(R.layout.item_advertisement)
                onBind {
                    textViewAdvertisement?.text = it
                }
                onClick/*(R.id.textViewAdvertisement)*/ { id, item ->
                    logs("(${R.id.textViewAdvertisement}, $id) -> $item", LogType.ERROR)
                }
            }

            withViewType<Int> {
                withLayoutResId(R.layout.item_loadmore)
                onBind {
                    textViewLoadMore?.text = it.toString()
                }
                onClick/*(R.id.textViewLoadMore)*/ { id, item ->
                    logs("(${R.id.textViewLoadMore}, $id) -> $item", LogType.ERROR)
                }
            }

            withItems(mutableListOf("one", 1, "two", 2, "three", 3, "four", 4, "five", 5, "six", 6, "seven", 7))
        }

        recyclerView.postDelayed({
            adapter + "eight"
        }, 2_000)

        recyclerView.postDelayed({
            adapter + mutableListOf(8, "nine", "ten")
        }, 4_000)

        recyclerView.postDelayed({
            adapter[2] =  "two - 2"
        }, 4_000)

        /*recyclerView.postDelayed({
            adapter.clear()
        }, 8_000)*/
    }
}