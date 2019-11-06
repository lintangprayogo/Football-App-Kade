package com.lintang.second.base.modular.source


import com.lintang.second.base.BaseDataSorce
import com.lintang.second.base.modular.model.league.SingleLeague
import com.lintang.second.base.modular.model.match.SingleMatch

interface DataSource : BaseDataSorce {
    fun getLeagueDetail(idLeague: String, callback: GetRemoteCallback<List<SingleLeague>>)
    fun getNextMatch(idLeague: String, callback: GetRemoteCallback<List<SingleMatch>>)
    fun getPrevMatch(idLeague: String, callback: GetRemoteCallback<List<SingleMatch>>)
    fun getSearchMatch(event: String, callback: GetRemoteCallback<List<SingleMatch>>)
    fun getMatchDetail(idEvent: String, callback: GetRemoteCallback<List<SingleMatch>>)

    //callback
    interface GetRemoteCallback<T> : BaseDataSorce.ResponseCallback<T>

}