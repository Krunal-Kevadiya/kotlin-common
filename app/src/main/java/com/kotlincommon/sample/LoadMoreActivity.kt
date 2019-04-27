package com.kotlincommon.sample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.loadmore.ILoadMore
import com.kotlinlibrary.loadmore.LoadMore
import com.kotlinlibrary.loadmore.LoadMoreSide
import kotlin.random.Random

class LoadMoreActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }

    /*private val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
    }*/

    private val adapter: MoviesAdapter by lazy {
        MoviesAdapter(mutableListOf())
    }

    private var count: Int = 1
    private lateinit var loadMore: ILoadMore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loadmore)

        recyclerView.adapter = adapter
        /*swipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                adapter.setListItem(MutableList(20) { index -> "Refresh -> ${index + 1}"}, LoadMoreSide.UP_SIDE)
                adapter.notifyDataSetChanged()
                swipeRefreshLayout.isRefreshing = false
                loadMore.resetLoadMore()
            }, 5000)
        }*/

        adapter.setListItem(MutableList(20) { index -> "Item -> ${index + 1}"}, LoadMoreSide.UP_SIDE)
        adapter.notifyDataSetChanged()
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
                //if (!swipeRefreshLayout.isRefreshing) {
                    loadMore.onLoadMoreBegin {
                        adapter.addOrRemoveItem(true, loadMore.getLoadMoreSide())
                    }
                    Handler(Looper.getMainLooper()).postDelayed({
                        if (Random(100).nextInt() % 2 == 0) {
                            count++
                            loadMore.onLoadMoreSucceed(count < 10) {
                                adapter.addOrRemoveItem(false, loadMore.getLoadMoreSide())
                            }
                            recyclerViews.post {
                                if (loadMore.getLoadMoreSide() == LoadMoreSide.DOWN_SIDE) {
                                    val list = MutableList(10) { index -> "LoadMore -> ${index + (adapter.itemCount + 1)}" }
                                    //val startIndex: Int = adapter.itemCount + 1
                                    //val itemCount: Int = list.count()
                                    adapter.addMoreItem(list, loadMore.getLoadMoreSide())
                                    adapter.notifyDataSetChanged()
                                    //adapter.notifyItemRangeInserted(startIndex, itemCount)
                                } else if (loadMore.getLoadMoreSide() == LoadMoreSide.UP_SIDE) {
                                    val list = MutableList(10) { index -> "LoadMore -> ${index + (adapter.itemCount + 1)}" }
                                    //val itemCount: Int = list.count()
                                    adapter.addMoreItem(list, loadMore.getLoadMoreSide())
                                    adapter.notifyDataSetChanged()
                                    //adapter.notifyItemRangeInserted(0, itemCount)
                                }
                            }
                        } else {
                            loadMore.onLoadMoreFailed {
                                adapter.addOrRemoveItem(true, loadMore.getLoadMoreSide())
                            }
                        }
                    }, 5000)
                //}
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
                Log.e("Data", "$position - $movie")
                holder.title.text = movie
            } else {
                Log.e("Data", "$position - ")
                holder.title.text = ""
            }
        }

        fun setListItem(data: MutableList<String>, type: LoadMoreSide) {
            mList.clear()
            if(type == LoadMoreSide.UP_SIDE)
                data.reverse()
            mList.addAll(data)
        }

        fun addMoreItem(data: MutableList<String>, type: LoadMoreSide){
            if(type == LoadMoreSide.UP_SIDE) {
                data.reverse()
                mList.addAll(0, data)
            } else {
                mList.addAll(data)
            }
        }

        fun addOrRemoveItem(isAdd: Boolean, type: LoadMoreSide) {
            /*if(type == LoadMoreSide.UP_SIDE) {
                if(isAdd) mList.add(0, "") else mList.removeAt(0)
            } else {
                if(isAdd) mList.add("") else mList.removeAt(mList.size-1)
            }*/
            //notifyDataSetChanged()
        }
    }
}

