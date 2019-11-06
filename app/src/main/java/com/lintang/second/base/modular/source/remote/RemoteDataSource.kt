package com.lintang.second.base.modular.source.remote

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lintang.second.base.modular.model.league.LeagueResponse
import com.lintang.second.base.modular.model.league.SingleLeague
import com.lintang.second.base.modular.model.match.MatchResponse
import com.lintang.second.base.modular.model.match.SearchMatchResponse
import com.lintang.second.base.modular.model.match.SingleMatch
import com.lintang.second.base.modular.source.DataSource
import com.lintang.second.base.modular.util.NullAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RemoteDataSource : DataSource {


    val leagueListAdapter: Gson by lazy {
        GsonBuilder().registerTypeAdapter(SingleLeague::class.java, NullAdapter()).create()
    }
    val matchListAdapter: Gson by lazy {
        GsonBuilder().registerTypeAdapter(SingleMatch::class.java, NullAdapter()).create()
    }

    override fun getLeagueDetail(
        idLeague: String,
        callback: DataSource.GetRemoteCallback<List<SingleLeague>>
    ) {
        ApiService.getApiService
            .getLeagueDetail(idLeague)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.onShowProgressDialog() }
            .doOnTerminate { callback.onHideProgressDialog() }
            .subscribe(object : ApiCallback<LeagueResponse>() {
                override fun onSuccess(model: LeagueResponse) {
                    val oldData = model.leagues
                    val newData = ArrayList<SingleLeague>()
                    for (element in oldData!!) {
                        newData.add(
                            Gson().fromJson(
                                leagueListAdapter.toJson(element),
                                SingleLeague::class.java
                            )
                        )
                    }
                    callback.onSuccess(newData)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }


    override fun getNextMatch(
        idLeague: String,
        callback: DataSource.GetRemoteCallback<List<SingleMatch>>
    ) {
        ApiService.getApiService.getNextMatch(idLeague).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.onShowProgressDialog() }
            .doOnTerminate { callback.onHideProgressDialog() }
            .subscribe(object : ApiCallback<MatchResponse>() {
                override fun onSuccess(model: MatchResponse) {
                    val oldData = model.events
                    val newData = ArrayList<SingleMatch>()
                    if (oldData != null) {
                        for (element in oldData) {
                            newData.add(
                                Gson().fromJson(
                                    matchListAdapter.toJson(element), SingleMatch::class.java
                                )
                            )
                        }
                    }
                    Log.d("ID ", idLeague + "${newData.size}")
                    callback.onSuccess(newData)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getPrevMatch(
        idLeague: String,
        callback: DataSource.GetRemoteCallback<List<SingleMatch>>
    ) {
        ApiService.getApiService.getPrevMatch(idLeague).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.onShowProgressDialog() }
            .doOnTerminate { callback.onHideProgressDialog() }
            .subscribe(object : ApiCallback<MatchResponse>() {
                override fun onSuccess(model: MatchResponse) {
                    val oldData = model.events
                    val newData = ArrayList<SingleMatch>()
                    if (oldData != null) {
                        for (element in oldData) {
                            newData.add(
                                Gson().fromJson(
                                    matchListAdapter.toJson(element), SingleMatch::class.java
                                )
                            )
                        }
                    }
                    Log.d("ID ", idLeague + "${newData.size}")
                    callback.onSuccess(newData)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })

    }

    override fun getSearchMatch(
        event: String,
        callback: DataSource.GetRemoteCallback<List<SingleMatch>>
    ) {
        ApiService.getApiService.getSearchMatch(event).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.onShowProgressDialog() }
            .doOnTerminate { callback.onHideProgressDialog() }
            .subscribe(object : ApiCallback<SearchMatchResponse>() {
                override fun onSuccess(model: SearchMatchResponse) {
                    val oldData = model.events
                    val newData = ArrayList<SingleMatch>()
                    if (oldData != null) {
                        for (element in oldData) {
                            newData.add(
                                Gson().fromJson(
                                    matchListAdapter.toJson(element), SingleMatch::class.java
                                )
                            )
                        }
                    }
                    callback.onSuccess(newData)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }

    override fun getMatchDetail(
        idEvent: String,
        callback: DataSource.GetRemoteCallback<List<SingleMatch>>
    ) {
        ApiService.getApiService.getMatchDetail(idEvent).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { callback.onShowProgressDialog() }
            .doOnTerminate { callback.onHideProgressDialog() }
            .subscribe(object : ApiCallback<MatchResponse>() {
                override fun onSuccess(model: MatchResponse) {
                    val oldData = model.events
                    val newData = ArrayList<SingleMatch>()
                    if (oldData != null) {
                        for (element in oldData) {
                            newData.add(
                                Gson().fromJson(
                                    matchListAdapter.toJson(element), SingleMatch::class.java
                                )
                            )
                        }
                    }
                    callback.onSuccess(newData)
                }

                override fun onFailure(code: Int, errorMessage: String) {
                    callback.onFailed(code, errorMessage)
                }

                override fun onFinish() {
                    callback.onFinish()
                }
            })
    }
}