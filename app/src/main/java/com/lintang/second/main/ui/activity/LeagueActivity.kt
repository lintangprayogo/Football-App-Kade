package com.lintang.second.main.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.lintang.second.R
import com.lintang.second.base.BaseAppActivity
import com.lintang.second.base.modular.model.league.SingleLeague
import com.lintang.second.main.adapter.LeagueAdapterItem
import com.lintang.second.main.adapter.MyPagerAdapter
import com.lintang.second.main.ui.fragment.NextFragment
import com.lintang.second.main.ui.fragment.PrevFragment
import com.lintang.second.main.viewmodel.LeagueViewModel
import com.lintang.second.main.viewmodel.match.MatchPrevViewModel
import com.lintang.second.main.viewmodel.match.MatchViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_league.*

class LeagueActivity : BaseAppActivity() {
    private lateinit var mViewModel: LeagueViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)
        setUpViewModel()

        if (checkExtra("data")) {
            val item = baseGetExtraData<LeagueAdapterItem>("data")
            setActionBarTitle(item.leagueName)
            mViewModel.getLeague(item.id)
        }
        setUpPager()
    }

    private fun setUpViewModel() {
        mViewModel = obtainLeagueViewModel().apply {
            leagueNextListLive.observe(this@LeagueActivity, Observer {
                setUpData(it[0])
            }
            )
            eventShowProgress.observe(this@LeagueActivity, Observer {
                setupEventProgressView(load_next, it)
            })

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (checkExtra("data")) {
            val item: LeagueAdapterItem = baseGetExtraData("data")
            baseStartActivity<SearchActivity, LeagueAdapterItem>(this, "data", item)
        }
        return true
    }

    private fun setUpPager() {
        val adapter = MyPagerAdapter(
            supportFragmentManager,
            listOf(PrevFragment(), NextFragment()),
            listOf("Prev Match", "Next Match")
        )
        viewpager.adapter = adapter
        tab.setupWithViewPager(viewpager)
    }

    fun obtainLeagueViewModel(): LeagueViewModel = obtainViewModel(
        LeagueViewModel::class.java
    )

    fun obtainNextMatchViewModel(): MatchViewModel = obtainViewModel(
        MatchViewModel::class.java
    )

    fun obtainPrevMatchViewModel(): MatchPrevViewModel = obtainViewModel(
        MatchPrevViewModel::class.java
    )


    fun setUpData(data: SingleLeague) {
        country_content.text = data.intFormedYear.toString()
        division_content.text = data.strCountry
        Picasso.get().load(data.strBadge).into(badge)
        Picasso.get().load(data.strTrophy).into(trophy)
        group.visibility = View.VISIBLE

        facebook.setOnClickListener {
            openUrl(this, data.strFacebook)
        }
        youtube.setOnClickListener {
            openUrl(this, data.strYoutube)
        }
    }

}
