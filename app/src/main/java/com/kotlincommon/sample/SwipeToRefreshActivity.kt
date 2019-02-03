package com.kotlincommon.sample

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.kotlinlibrary.recycleradapter.setUp
import com.kotlinlibrary.swiperefreshlayout.WaveSwipeRefreshLayout
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs
import kotlinx.android.synthetic.main.activity_swipe_to_refresh.*
import kotlinx.android.synthetic.main.item_advertisement.view.*
import java.util.*

class SwipeToRefreshActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_to_refresh)

        val adapter = recyclerView.setUp<String> {
            withLayoutResId(R.layout.item_advertisement)
            onBind {
                setBackgroundColor(getRandomColor())
                textViewAdvertisement.text = it
            }
            onClick{ id, item ->
                logs("(${R.id.textViewAdvertisement}, $id) -> $item", LogType.ERROR)
            }
            withItems(mutableListOf("one", "two", "three", "four", "five", "six", "seven"))
        }

        wsrl_main.setColorSchemeColors(Color.WHITE, Color.WHITE)
        wsrl_main.setWaveColor(Color.rgb(116, 170, 80))
        wsrl_main.setOnRefreshListener(object : WaveSwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                Handler().postDelayed({
                    adapter + mutableListOf("one", "two", "three", "four", "five", "six", "seven")
                    wsrl_main.isRefreshing = false
                }, 5000)
            }
        })
    }

    private fun getRandomColor(): Int {
        val rnd = Random(System.nanoTime())
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}