package com.lintang.second.base.ui.adapter

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecylerViewAdapter<T, Holder : BaseHolder<T>> : RecyclerView.Adapter<Holder>() {
    protected lateinit var mContext: Context
    protected lateinit var mListener: (T) -> Unit

    protected val mRecyclerViewDataList = mutableListOf<T>()
    protected var mRecyclerViewLayout: Int = 0

    fun setRecyclerViewLayout(context: Context, layoutItem: Int) {
        mContext = context
        mRecyclerViewLayout = layoutItem
    }

    fun setRecyclerViewListener(listener: (T) -> Unit) {
        mListener = listener
    }

    fun setListData(list: List<T>) {
        mRecyclerViewDataList.clear()
        mRecyclerViewDataList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearData() {
        mRecyclerViewDataList.clear()
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItem(mRecyclerViewDataList[position], mListener)
    }

    override fun getItemCount(): Int = mRecyclerViewDataList.size
}