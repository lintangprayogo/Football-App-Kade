package com.lintang.second.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lintang.second.base.ui.adapter.BaseHolder
import com.lintang.second.base.ui.adapter.BaseRecylerViewAdapter
import kotlinx.android.synthetic.main.league_layout.view.*

class LeagueAdapter : BaseRecylerViewAdapter<LeagueAdapterItem, LeagueAdapter.LeagueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            LayoutInflater.from(mContext).inflate(
                mRecyclerViewLayout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(mRecyclerViewDataList[position], mListener)

    }

    inner class LeagueViewHolder(val view: View) : BaseHolder<LeagueAdapterItem>(view) {

        override fun initComponent(data: LeagueAdapterItem) {
            super.initComponent(data)
            view.league_name.text = data.leagueName
            view.badge.setImageResource(data.badge)
        }
    }

}