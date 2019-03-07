package com.kotlincommon.sample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kotlinlibrary.loadmore.ILoadMore
import com.kotlinlibrary.loadmore.LoadMore
import com.kotlinlibrary.loadmore.LoadMoreSide
import kotlin.random.Random

class LoadMoreActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }

    private val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
    }

    private val adapter: MoviesAdapter by lazy {
        MoviesAdapter(mutableListOf())
    }

    private var count: Int = 1
    private lateinit var loadMore: ILoadMore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loadmore)

        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                adapter.setListItem(MutableList(20) { index -> "Refresh -> $index"}, LoadMoreSide.UP_SIDE)
                swipeRefreshLayout.isRefreshing = false
                loadMore.resetLoadMore()
            }, 5000)
        }

        adapter.setListItem(MutableList(20) { index -> "Item -> $index"}, LoadMoreSide.UP_SIDE)
        setupLoadMore()
    }

    private fun setupLoadMore() {
        loadMore = LoadMore {
            context = this@LoadMoreActivity
            triggerThreshold = 5
            isErrorVisible = false
            isProgressVisible = true
            recyclerViews = recyclerView
            loadMoreSides = LoadMoreSide.UP_SIDE
            loadMoreListener = {
                if (!swipeRefreshLayout.isRefreshing) {
                    loadMore.onLoadMoreBegin()
                    Handler(Looper.getMainLooper()).postDelayed({
                        if (Random(100).nextInt() % 2 == 0) {
                            count++
                            loadMore.onLoadMoreSucceed(count < 3)
                            if (loadMore.getLoadMoreSide() == LoadMoreSide.DOWN_SIDE) {
                                adapter.addLastListItem(MutableList(10) { index -> "LoadMore -> ${index + (adapter.itemCount + 1)}" })
                            } else if (loadMore.getLoadMoreSide() == LoadMoreSide.UP_SIDE) {
                                adapter.addFirstListItem(MutableList(10) { index -> "LoadMore -> ${index + (adapter.itemCount + 1)}" })
                            }
                        } else {
                            loadMore.onLoadMoreFailed()
                        }
                    }, 5000)
                }
            }
            /*customView = { relativeLayout, textView, progressBar ->
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, resources.getDimension(R.dimen._5ssp))
            }*/
        }
    }

    inner class MoviesAdapter(var mList: MutableList<String>) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

        override fun getItemCount(): Int = mList.size

        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var title: TextView = view.findViewById(R.id.textViewLoadMore)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loadmore, parent, false)

            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            if(position >= 0 && position < mList.size) {
                val movie = mList[position]
                holder.title.text = movie
            }
        }

        fun setListItem(data: MutableList<String>, type: LoadMoreSide) {
            mList.clear()
            if(type == LoadMoreSide.UP_SIDE)
                data.reverse()
            mList.addAll(data)
            notifyDataSetChanged()
        }

        fun addLastListItem(data: MutableList<String>){
            val startIndex: Int = mList.size
            val itemCount: Int = data.count()
            mList.addAll(data)
            notifyItemRangeInserted(startIndex, itemCount)
        }

        fun addFirstListItem(data: MutableList<String>){
            data.reverse()
            val itemCount: Int = data.count()
            mList.addAll(0, data)
            notifyItemRangeInserted(0, itemCount)
        }
    }
}

