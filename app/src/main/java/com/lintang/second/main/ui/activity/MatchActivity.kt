package com.lintang.second.main.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.lintang.second.R
import com.lintang.second.base.BaseAppActivity
import com.lintang.second.base.modular.model.match.SingleMatch
import com.lintang.second.base.modular.util.Helper.Func.toDatetoString
import com.lintang.second.main.viewmodel.match.MatchDetailViewModel
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.cards_layout.*
import kotlinx.android.synthetic.main.goalers_layout.*
import kotlinx.android.synthetic.main.lineup_layout.*
import kotlinx.android.synthetic.main.match_item.*

class MatchActivity : BaseAppActivity() {
    private lateinit var mViewModel: MatchDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)
        setUpViewModel()
        supportActionBar?.hide()
        if (checkExtra("data")) {
            val item = baseGetExtraData<SingleMatch>("data")
            mViewModel.getDetailMatch(item.idEvent)
        }
    }

    private fun setUpViewModel() {
        mViewModel = obtainMatchDetailViewModel().apply {
            eventShowProgress.observe(this@MatchActivity, Observer {
                setupEventProgressView(match_detail_load, it)
            })
            matchDetailListLive.observe(this@MatchActivity, Observer {
                setUpData(it[0])
                main_content.visibility = View.VISIBLE
            })
        }
    }

    private fun obtainMatchDetailViewModel() = obtainViewModel(MatchDetailViewModel::class.java)
    @SuppressLint("SetTextI18n")
    private fun setUpData(data: SingleMatch) {
        date.text = toDatetoString(data.dateEvent, data.strTime, "EEE, dd MMM yyyy HH:mm")
        home.text = data.strHomeTeam
        away.text = data.strAwayTeam
        vs.text = "${data.intHomeScore} vs ${data.intAwayScore}"
        home_goalers.text = data.strHomeGoalDetails
        away_goalers.text = data.strAwayGoalDetails
        home_shot.text = data.intHomeShots
        away_shots.text = data.intAwayShots
        home_kepper.text = data.strHomeLineupGoalkeeper
        away_keeper.text = data.strAwayLineupGoalkeeper
        home_def.text = data.strHomeLineupDefense
        away_def.text = data.strAwayLineupDefense
        home_mid.text = data.strHomeLineupMidfield
        away_mid.text = data.strAwayLineupMidfield
        home_forward.text = data.strHomeLineupForward
        away_forward.text = data.strAwayLineupForward
        home_substitutes.text = data.strHomeLineupSubstitutes
        away_substitutes.text = data.strAwayLineupSubstitutes
        away_yellow.text = data.strAwayYellowCards
        home_yellow.text = data.strHomeYellowCards
        away_red.text = data.strAwayRedCards
        home_red.text = data.strHomeRedCards
    }


}
