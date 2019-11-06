package com.lintang.second.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lintang.second.base.modular.model.match.SingleMatch
import com.lintang.second.base.modular.util.Helper.Func.toDatetoString
import com.lintang.second.base.ui.adapter.BaseHolder
import com.lintang.second.base.ui.adapter.BaseRecylerViewAdapter
import kotlinx.android.synthetic.main.match_item.view.*


class MatchAdapter : BaseRecylerViewAdapter<SingleMatch, MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            LayoutInflater.from(mContext).inflate(
                mRecyclerViewLayout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(mRecyclerViewDataList[position], mListener)

    }

    inner class MatchViewHolder(val view: View) : BaseHolder<SingleMatch>(view) {

        @SuppressLint("SetTextI18n")
        override fun initComponent(data: SingleMatch) {
            super.initComponent(data)
            view.away.text = data.strAwayTeam
            view.home.text = data.strHomeTeam
            view.vs.text = "${data.intHomeScore} vs ${data.intAwayScore}"
            view.date.text = toDatetoString(data.dateEvent, data.strTime, "EEE, dd MMM yyyy HH:mm")
        }
    }

}