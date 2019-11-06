package com.lintang.second.main.viewmodel

import android.app.Application
import com.lintang.second.base.BaseViewModel
import com.lintang.second.base.modular.model.league.SingleLeague
import com.lintang.second.base.modular.source.DataSource
import com.lintang.second.base.modular.source.Repository
import com.lintang.second.base.modular.util.SingleLiveEvent

class LeagueViewModel(val context: Application, private val repository: Repository) :
    BaseViewModel(context) {
    var leagueNextListLive = SingleLiveEvent<List<SingleLeague>>()

    fun getLeague(idLeague: String) {
        repository.getLeagueDetail(
            idLeague,
            object : DataSource.GetRemoteCallback<List<SingleLeague>> {
                override fun onEmpty() {
                    eventIsEmpty.postValue(true)
                }

                override fun onShowProgressDialog() {
                    eventShowProgress.postValue(true)
                }

                override fun onHideProgressDialog() {
                    eventShowProgress.postValue(false)
                }

                override fun onSuccess(data: List<SingleLeague>) {
                    leagueNextListLive.postValue(data)
                }

                override fun onFinish() {

                }

                override fun onFailed(statusCode: Int, errorMessage: String?) {

                }
            })
    }

}