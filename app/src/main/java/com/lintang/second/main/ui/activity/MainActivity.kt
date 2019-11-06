package com.lintang.second.main.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lintang.second.R
import com.lintang.second.R.array.*
import com.lintang.second.base.BaseAppActivity
import com.lintang.second.main.adapter.LeagueAdapter
import com.lintang.second.main.adapter.LeagueAdapterItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAppActivity() {
    val list: MutableList<LeagueAdapterItem> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        val adapter = LeagueAdapter()
        adapter.setRecyclerViewLayout(this, R.layout.league_layout)
        adapter.setListData(list)
        adapter.setRecyclerViewListener {
            baseStartActivity<LeagueActivity, LeagueAdapterItem>(this, "data", it)
        }

        list_rv_league.adapter = adapter
        list_rv_league.layoutManager = LinearLayoutManager(this)

    }

    fun initData() {
        val leagueNames = resources.getStringArray(leagues_name)
        val leagueIds = resources.getStringArray(leagues_id)
        val teamEmblem = resources.obtainTypedArray(leagues_emblem)
        list.clear()
        for (i in leagueNames.indices) {
            list.add(
                LeagueAdapterItem(
                    teamEmblem.getResourceId(i, 0),
                    leagueNames[i],
                    leagueIds[i]
                )
            )
        }

        teamEmblem.recycle()
    }

}
