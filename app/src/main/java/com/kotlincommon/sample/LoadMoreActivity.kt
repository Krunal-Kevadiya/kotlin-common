package com.kotlincommon.sample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kotlinlibrary.loadmore.ILoadMore
import com.kotlinlibrary.loadmore.LoadMore
import com.kotlinlibrary.loadmore.LoadMoreSides
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
        adapter.setListItem(MutableList(20) { index -> "Item -> $index"})

        swipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                adapter.setListItem(MutableList(15) { index -> "Refresh -> $index"})
                swipeRefreshLayout.isRefreshing = false
                loadMore.resetLoadMore()
            }, 5000)
        }

        setupLoadMore {
            Log.e("LoadMore", "onLoadMoreBegin")
            loadMore.onLoadMoreBegin()
            Handler(Looper.getMainLooper()).postDelayed({
                if(Random(100).nextInt() % 2 == 0) {
                    if (loadMore.getLoadMoreSide() == LoadMoreSides.DOWN_SIDE) {
                        adapter.addLastListItem(MutableList(10) { index -> "LoadMore -> ${index + this.adapter.itemCount}" })
                    } else if(loadMore.getLoadMoreSide() == LoadMoreSides.UP_SIDE) {
                        adapter.addFirstListItem(MutableList(10) { index -> "LoadMore -> ${index + this.adapter.itemCount}" })
                    }
                    count++
                    loadMore.onLoadMoreSucceed(count < 3)
                    Log.e("LoadMore", "onLoadMoreSucceed $count , ${count < 3}")
                } else {
                    Log.e("LoadMore", "onLoadMoreFailed")
                    loadMore.onLoadMoreFailed()
                }
            }, 5000)
        }
    }

    fun setupLoadMore(onLoadMoreListener: () -> Unit) {
        loadMore = LoadMore.Builder(this)
            .setRecyclerView(recyclerView)
            .setLoadMoreSide(LoadMoreSides.UP_SIDE)
            .setLoginProgressBarVisible(true)
            .setTriggerThreshold(5)
            /*.setCustomView { relativeLayout, textView, progressBar ->
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, resources.getDimension(R.dimen._5ssp))
            }*/
            .setLoadMoreListener {
                if (!swipeRefreshLayout.isRefreshing) {
                    onLoadMoreListener()
                }
            }
            .build()
    }
}

class MoviesAdapter(var mList: MutableList<String>) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

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
        if (position >= 0 && position < mList.size) {
            val movie = mList[position]
            holder.title.text = movie
        }
    }

    fun setListItem(data: MutableList<String>){
        mList.clear()
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