package com.lintang.second.base.ui.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    open fun bindItem(data: T, listener: (T) -> Unit) {
        onItemViewClicked(data, listener)
        initComponent(data)
    }

    protected fun onItemViewClicked(data: T, listener: (T) -> Unit) {
        Log.d("TAG", data.toString())
        itemView.setOnClickListener {
            listener(data)
        }
    }

    open fun initComponent(data: T) {}
}