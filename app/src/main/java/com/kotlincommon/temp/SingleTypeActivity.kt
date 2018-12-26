package com.kotlincommon.temp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlincommon.sample.R
import com.kotlinlibrary.recycleradapter.kidadapter.setUp
import kotlinx.android.synthetic.main.activity_recycler_adapter.*
import kotlinx.android.synthetic.main.item_advertisement.view.*
import java.util.*

class SingleTypeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_adapter)

        val adapter = recyclerView.setUp<String> {
            withLayoutResId(R.layout.item_advertisement)
            withItems(mutableListOf("one", "two", "three", "four", "five", "six", "seven"))
            bind {
                setBackgroundColor(getRandomColor())
                textView.text = it
            }
        }

        recyclerView.postDelayed({ adapter + "1" }, 2_000)
        recyclerView.postDelayed({ adapter + mutableListOf("2", "3", "4") }, 4_000)
        recyclerView.postDelayed({ adapter[2] =  "3" }, 4_000)
        recyclerView.postDelayed({ adapter.clear() }, 8_000)
    }

    private fun getRandomColor(): Int {
        val rnd = Random(System.nanoTime())
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}