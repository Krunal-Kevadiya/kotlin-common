package com.kotlincommon.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.kotlincommon.sample.databinding.ItemAdvertisementBinding
import com.kotlincommon.sample.databinding.ItemLoadmoreBinding
import com.kotlinlibrary.recycleradapter.setUpBinding
import com.kotlinlibrary.recycleradapter.typed.MultiTypedBindingAdapter
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs
import kotlinx.android.synthetic.main.activity_recycler_adapter.*

class MultiTypeActivity : AppCompatActivity() {

    lateinit var adapter: MultiTypedBindingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_adapter)

        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        adapter = recyclerView.setUpBinding {
            withDiffUtils { false }
            withContentComparator { s1, s2 -> s1 == s2 }
            withItemsComparator { s1, s2 ->  s1.hashCode() == s2.hashCode()}

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
                    adapter - index
                }
            }

            withViewType<Int> {
                withLayoutResId(R.layout.item_loadmore)
                /*onBind { index, item ->
                    textViewLoadMore?.text = it.toString()
                }*/
                onBind<ItemLoadmoreBinding>(-1) { _, item ->
                    this.textViewLoadMore.text = item.toString()
                }
                onClick/*(R.id.textViewLoadMore)*/ { id, _, item ->
                    logs("(${R.id.textViewLoadMore}, $id) -> $item", LogType.ERROR)
                    adapter.add((adapter.getItemLists().size - 1), "")
                }
            }

            withItems(mutableListOf("one", 1, "two", 2))
        }

        /*recyclerView.postDelayed({
            adapter - "eight"
        }, 2_000)

        recyclerView.postDelayed({
            adapter.reSet(mutableListOf(8, "nine", "ten"))
        }, 4_000)

        recyclerView.postDelayed({
            adapter[2] =  "two - 2"
        }, 6_000)

        recyclerView.postDelayed({
            adapter.clear()
        }, 8_000)*/
    }
}