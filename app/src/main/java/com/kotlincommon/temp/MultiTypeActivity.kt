package com.kotlincommon.temp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.kotlincommon.sample.R
import com.kotlinlibrary.recycleradapter.kidadapter.setUp
import kotlinx.android.synthetic.main.activity_recycler_adapter.recyclerView
import kotlinx.android.synthetic.main.item_advertisement.textView

class MultiTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_adapter)

        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setUp {
            withViewType {
                withLayoutResId(R.layout.item_advertisement)
                bind<String> {
                    textView.text = it
                }
            }

            withViewType {
                withLayoutResId(R.layout.item_loadmore)
                bind<Int> {
                    textView.text = it.toString()
                }
            }

            withItems(mutableListOf("one", 1, "two", 2, "three", 3, "four", 4, "five", 5, "six", 6, "seven", 7))
        }

    }
}