package com.kotlincommon.sample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlincommon.sample.databinding.ItemAdvertisementBinding
import com.kotlinlibrary.recycleradapter.setUpBinding
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs
import kotlinx.android.synthetic.main.activity_recycler_adapter.*
import java.util.*

class SingleTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_adapter)

        val adapter = recyclerView.setUpBinding<String> {
            withLayoutResId(R.layout.item_advertisement)
            /*onBind { index, item ->
                setBackgroundColor(getRandomColor())
                textViewAdvertisement.text = it
            }*/
            onBind<ItemAdvertisementBinding>(BR.viewModel) { index, item ->
                //this.textViewAdvertisement.setTextColor(Color.BLUE)
            }
            onClick/*(R.id.textViewAdvertisement)*/ { id, item ->
                logs("(${R.id.textViewAdvertisement}, $id) -> $item", LogType.ERROR)
            }
            withItems(mutableListOf("one", "two", "three", "four", "five", "six", "seven"))
        }

        recyclerView.postDelayed({
            adapter + "1"
        }, 2_000)

        recyclerView.postDelayed({
            adapter + mutableListOf("2", "3", "4")
        }, 4_000)

        recyclerView.postDelayed({
            adapter[2] = "3"
        }, 4_000)

        /*recyclerView.postDelayed({
            adapter.clear()
        }, 8_000)*/
    }

    private fun getRandomColor(): Int {
        val rnd = Random(System.nanoTime())
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}