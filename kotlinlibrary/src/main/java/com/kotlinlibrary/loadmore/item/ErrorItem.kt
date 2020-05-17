package com.kotlinlibrary.loadmore.item

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlibrary.R
import com.kotlinlibrary.loadmore.callback.OnRepeatListener
import com.kotlinlibrary.utils.ktx.fromApi
import com.kotlinlibrary.utils.ktx.toApi

interface ErrorItem {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, onRepeatListener: OnRepeatListener?)

    companion object {
        val DEFAULT: ErrorItem = object : ErrorItem {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_error, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, onRepeatListener: OnRepeatListener?) {
                val btnRepeat = holder.itemView.findViewById<View>(R.id.btnRepeat) as Button
                fromApi(Build.VERSION_CODES.LOLLIPOP, true) {
                    btnRepeat.setBackgroundResource(R.drawable.loadmore_button_ripple)
                }
                toApi(Build.VERSION_CODES.LOLLIPOP) {
                    btnRepeat.setBackgroundResource(R.drawable.loadmore_button_selector)
                }

                btnRepeat.setOnClickListener {
                    onRepeatListener?.onClickRepeat()
                }
            }
        }
    }
}
