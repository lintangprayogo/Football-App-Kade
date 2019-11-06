package com.lintang.second.base.modular.source

import com.lintang.second.base.modular.model.league.SingleLeague
import com.lintang.second.base.modular.model.match.SingleMatch
import com.lintang.second.base.modular.source.remote.RemoteDataSource

open class Repository(val remoteDataSource: RemoteDataSource) : DataSource {

    override fun getPrevMatch(
        idLeague: String,
        callback: DataSource.GetRemoteCallback<List<SingleMatch>>
    ) {
        remoteDataSource.getPrevMatch(idLeague, callback)
    }

    override fun getLeagueDetail(
        idLeague: String,
        callback: DataSource.GetRemoteCallback<List<SingleLeague>>
    ) {
        remoteDataSource.getLeagueDetail(idLeague, callback)
    }


    override fun getNextMatch(
        idLeague: String,
        callback: DataSource.GetRemoteCallback<List<SingleMatch>>
    ) {
        remoteDataSource.getNextMatch(idLeague, callback)

    }

    override fun getSearchMatch(
        event: String,
        callback: DataSource.GetRemoteCallback<List<SingleMatch>>
    ) {
        remoteDataSource.getSearchMatch(event, callback)
    }

    override fun getMatchDetail(
        idEvent: String,
        callback: DataSource.GetRemoteCallback<List<SingleMatch>>
    ) {
        remoteDataSource.getMatchDetail(idEvent, callback)
    }


    companion object {
        private var INSTANCE: Repository? = null
        @JvmStatic
        fun getInstance(RemoteDataSource: RemoteDataSource) =
            INSTANCE
                ?: synchronized(Repository::class.java) {
                    INSTANCE
                        ?: Repository(RemoteDataSource)
                            .also { INSTANCE = it }
                }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}