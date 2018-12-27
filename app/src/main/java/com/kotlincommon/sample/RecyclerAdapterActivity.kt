package com.kotlincommon.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.recycleradapter.BaseViewHolder
import com.kotlinlibrary.recycleradapter.adapter.SimpleBasicRecyclerAdapter
import com.kotlinlibrary.utils.LogType
import com.kotlinlibrary.utils.logs
import kotlinx.android.synthetic.main.item_advertisement.view.*
import kotlinx.android.synthetic.main.item_loadmore.view.*

class RecyclerAdapterActivity : AppCompatActivity() {
    private val adapter = SimpleBasicRecyclerAdapter().apply {
        addViewHolder(User::class, R.layout.item_loadmore) {
            UserViewHolder(it) { item, index, viewId ->
                logs("item -> $item ,index -> $index ,viewId -> $viewId", LogType.ERROR)
            }
        }
        addViewHolder(Advertisement::class, R.layout.item_advertisement) {
            AdvertisementViewHolder(it)
        }
    }

    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_adapter)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.items = listOf(
            User("FirstUser 1"),
            User("FirstUser 2"),
            Advertisement("http://advertisement.com/1"),
            User("FirstUser 3"),
            User("FirstUser 4"),
            User("FirstUser 5"),
            Advertisement("http://advertisement.com/2"),
            User("FirstUser 6"),
            User("FirstUser 7"),
            User("FirstUser 8"),
            User("FirstUser 9"),
            User("FirstUser 10")
        )
    }
}



class User(val name: String)
data class Advertisement(val url: String)

class UserViewHolder(itemView: View, val onClickListener: (item: User, index: Int, viewId: Int) -> Unit)
    : RecyclerView.ViewHolder(itemView), BaseViewHolder<User> {
    override fun bind(item: User) {
        itemView.textViewLoadMore.text = item.name
        itemView.textViewLoadMore.setOnClickListener {
            onClickListener(item, adapterPosition, itemView.textViewLoadMore.id)
        }
    }
}
class AdvertisementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), BaseViewHolder<Advertisement> {
    override fun bind(item: Advertisement) {
        itemView.textViewAdvertisement.text = item.url
    }
}

/*************************************************************
//Advance Adapter
private val adapter = AdvancedSimpleRecyclerAdapter().apply {
    addViewHolder(User::class, R.layout.viewholder_user) {
        UserViewHolder(it) { item, index, viewId ->
        }
    }
    addViewHolder(Advertisement::class, R.layout.viewholder_ad) {
        AdvertisementViewHolder(it)
    }
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_basic_example)

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter

    btnSetList.setOnClickListener { initialize() } // list initialization
    btnInsertSingle.setOnClickListener { insertSingle() } //added single item
    btnInsertList.setOnClickListener { insertList() } //added list of item
    btnRemove.setOnClickListener { remove() } //remove item
    btnMove.setOnClickListener { move() } //move current position to specific position
    btnReplace.setOnClickListener { replace() } //replace item by new value

    initialize()
}

private fun generateRandomItem() =
    if (Random().nextInt(2) == 0)
        generateRandomUser()
    else
        generateRandomAd()

private fun generateRandomUser() = User("FirstUser ${Random().nextInt()}")

private fun generateRandomAd() = Advertisement("http://advertisement.com/${Random().nextInt()}")

private fun initialize() {
    adapter.set((0..2).map { generateRandomItem() })
}

private fun insertSingle() {
    val position = Random().nextInt(adapter.itemCount)
    adapter.insert(generateRandomItem(), position)
}

private fun insertList() {
    val positionStart = Random().nextInt(adapter.itemCount)
    adapter.insert((0..2).map { generateRandomItem() }, positionStart)
}

private fun remove() {
    if(adapter.itemCount == 0) return
    val position = Random().nextInt(adapter.itemCount)
    adapter.remove(position)
}

private fun move() {
    if(adapter.itemCount < 2) return
    val fromPosition = Random().nextInt(adapter.itemCount)
    val toPosition = Random().nextInt(adapter.itemCount)
    adapter.move(fromPosition, toPosition)
}

private fun replace() {
    if(adapter.itemCount == 0) return
    val position = Random().nextInt(adapter.itemCount)
    adapter.replace(generateRandomItem(), position)
}
*************************************************************/