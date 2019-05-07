package com.kotlincommon.sample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kotlincommon.sample.databinding.ItemLoadmoreBinding
import com.kotlinlibrary.loadmore.item.ErrorItem
import com.kotlinlibrary.loadmore.item.LoadingItem
import com.kotlinlibrary.loadmore.paginate.Direction
import com.kotlinlibrary.loadmore.paginate.NoPaginate
import com.kotlinlibrary.recycleradapter.setUpBinding
import com.kotlinlibrary.recycleradapter.simple.SingleBindingAdapter
import kotlin.random.Random
import com.kotlinlibrary.loadmore.callback.OnRepeatListener
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button

class LoadMoreActivity : AppCompatActivity() {

    private val recyclerViews: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }

    private val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
    }

    private lateinit var adapter: SingleBindingAdapter<String>

    private var count: Int = 1
    private lateinit var noPaginate: NoPaginate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loadmore)
        adapter = recyclerViews.setUpBinding<String> {
            withLayoutResId(R.layout.item_loadmore)
            onBind<ItemLoadmoreBinding>(BR.viewModel) { _, item ->
                this.textViewLoadMore.text = item
            }
            onClick { _, _, _ ->
            }
            withItems(MutableList(20) { index -> "Item -> ${index + 1}" })
        }
        swipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                val list = MutableList(20) { index -> "Refresh -> ${index + 1}" }
                adapter.reSet(list)
                swipeRefreshLayout.isRefreshing = false
                noPaginate.setResetItems()
            }, 5000)
        }
        setupLoadMore()
    }

    private fun setupLoadMore() {

        noPaginate = NoPaginate {
           loadingTriggerThreshold = 0
            recyclerView = recyclerViews
            loadingItem = LoadingItem.DEFAULT
            errorItem = ErrorItem.DEFAULT
            // loadingItem = CustomLoadingItem()
            // errorItem = CustomErrorItem()
            direction = Direction.UP
            onLoadMore = {
                noPaginate.showError(false)
                noPaginate.showLoading(true)
                Handler(Looper.getMainLooper()).postDelayed({
                    if (Random(25).nextInt() % 2 == 0) {
                        count++
                        noPaginate.showLoading(false)
                        noPaginate.setNoMoreItems(count > 3)
                        recyclerView.post {
                            val list = MutableList(10) { index -> "LoadMore -> ${index + (adapter.itemCount + 1)}" }
                            adapter + list
                        }
                    } else {
                        noPaginate.showLoading(false)
                        noPaginate.showError(true)
                    }
                }, 5000)
            }
        }
    }

    override fun onDestroy() {
        noPaginate.unbind()
        super.onDestroy()
    }

    inner class CustomErrorItem : ErrorItem {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_error, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }

        override fun onBindViewHolder(
            holder: RecyclerView.ViewHolder,
            position: Int,
            repeatListener: OnRepeatListener?
        ) {
            val btnRepeat = holder.itemView.findViewById<Button>(R.id.btnRepeat)
            btnRepeat.setOnClickListener {
                repeatListener?.onClickRepeat()
            }
        }
    }

    inner class CustomLoadingItem : LoadingItem {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
    }
}
