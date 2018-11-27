package com.kotlincommon

import android.icu.text.UnicodeSetIterator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
                resetLoadMore()
            }, 5000)
        }

        setupLoadMore {
            onLoadMoreBegin()
            Handler(Looper.getMainLooper()).postDelayed({
                if(Random(100).nextInt() % 2 == 0) {
                    adapter.addListItem(MutableList(10) { index -> "LoadMore -> ${index + this.adapter.itemCount}" })
                    onLoadMoreSucceed(true)
                } else {
                    onLoadMoreFailed()
                }
            }, 5000)
        }
    }

    fun setupLoadMore(onLoadMoreListener: () -> Unit) {
        loadMore = LoadMore.Builder(this)
            .setRecyclerView(recyclerView)
            .setCustomView { linearLayout, textView ->
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, resources.getDimension(R.dimen._5ssp))
            }
            .setLoadMoreListener {
                if (!swipeRefreshLayout.isRefreshing) {
                    onLoadMoreListener()
                }
            }
            .build()
    }

    //Display Loader
    fun onLoadMoreBegin() {
        loadMore.onLoadMoreBegin("Please wait will load data.")
    }

    //Enable/Disable load more event
    fun onLoadMoreSucceed(hasMoreItems: Boolean) {
        loadMore.onLoadMoreSucceed(hasMoreItems)
    }

    //Display message for fail
    fun onLoadMoreFailed() {
        loadMore.onLoadMoreFailed()
    }

    //Reset event when refresh data, for example pull to refresh
    fun resetLoadMore() {
        loadMore.resetLoadMore()
    }

}

class MoviesAdapter(var mList: MutableList<String>) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    override fun getItemCount(): Int = mList.size

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_loadmore, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = mList[position]
        holder.title.text = movie
    }

    fun setListItem(data: MutableList<String>){
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    fun addListItem(data: MutableList<String>){
        val startIndex: Int = mList.size
        val itemCount: Int = data.count()
        mList.addAll(data)
        notifyItemRangeInserted(startIndex, itemCount)
    }
}