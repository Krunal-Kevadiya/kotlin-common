package com.kotlincommon.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.kotlincommon.sample.databinding.ItemAdvertisementBinding
import com.kotlincommon.sample.databinding.ItemLoadmoreBinding
import com.kotlinlibrary.recycleradapter.setUpBinding
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs
import kotlinx.android.synthetic.main.activity_recycler_adapter.*

class MultiTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_adapter)

        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val adapter = recyclerView.setUpBinding {

            withViewType<String> {
                withLayoutResId(R.layout.item_advertisement)
                /*onBind { index, item ->
                    textViewAdvertisement?.text = it
                }*/
                onBind<ItemAdvertisementBinding>(BR.viewModel) { index, item ->
                    this.textViewAdvertisement.text = item
                }
                onClick/*(R.id.textViewAdvertisement)*/ { id, index, item ->
                    logs("(${R.id.textViewAdvertisement}, $id) -> $item", LogType.ERROR)
                }
            }

            withViewType<Int> {
                withLayoutResId(R.layout.item_loadmore)
                /*onBind { index, item ->
                    textViewLoadMore?.text = it.toString()
                }*/
                onBind<ItemLoadmoreBinding>(-1) { index, item ->
                    this.textViewLoadMore.text = item.toString()
                }
                onClick/*(R.id.textViewLoadMore)*/ { id, index, item ->
                    logs("(${R.id.textViewLoadMore}, $id) -> $item", LogType.ERROR)
                }
            }

            withItems(mutableListOf("one", 1, "two", 2, "three", 3, "four", 4, "five", 5, "six", 6, "seven", 7))
        }

        recyclerView.postDelayed({
            adapter - "eight"
        }, 2_000)

        recyclerView.postDelayed({
            adapter.reSet(mutableListOf(8, "nine", "ten"))
        }, 4_000)

        recyclerView.postDelayed({
            adapter[2] =  "two - 2"
        }, 6_000)

        /*recyclerView.postDelayed({
            adapter.clear()
        }, 8_000)*/
    }
}