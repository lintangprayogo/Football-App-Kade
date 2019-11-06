package com.lintang.second.base

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lintang.second.base.modular.source.Repository
import com.lintang.second.base.modular.util.Injection
import com.lintang.second.main.viewmodel.LeagueViewModel
import com.lintang.second.main.viewmodel.match.MatchDetailViewModel
import com.lintang.second.main.viewmodel.match.MatchPrevViewModel
import com.lintang.second.main.viewmodel.match.MatchSearchViewModel
import com.lintang.second.main.viewmodel.match.MatchViewModel

class ViewModelFactory private constructor(
    private val mApplication: Application,
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {

                isAssignableFrom(LeagueViewModel::class.java) ->
                    LeagueViewModel(mApplication, repository)
                isAssignableFrom(MatchViewModel::class.java) ->
                    MatchViewModel(mApplication, repository)
                isAssignableFrom(MatchPrevViewModel::class.java) ->
                    MatchPrevViewModel(mApplication, repository)
                isAssignableFrom(MatchSearchViewModel::class.java) ->
                    MatchSearchViewModel(mApplication, repository)
                isAssignableFrom(MatchDetailViewModel::class.java) ->
                    MatchDetailViewModel(mApplication, repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(mApplication: Application) =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE
                        ?: ViewModelFactory(
                            mApplication,
                            Injection.provideRepository()
                        )
                            .also { INSTANCE = it }
                }
    }
}
