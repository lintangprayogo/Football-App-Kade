package com.lintang.second.base.modular.util

import com.lintang.second.base.modular.source.Repository
import com.lintang.second.base.modular.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance(RemoteDataSource)
    }
}
