package com.lintang.second.base.modular.source.remote

import com.lintang.second.BuildConfig
import com.lintang.second.base.BaseApplication
import com.lintang.second.base.modular.model.league.LeagueResponse
import com.lintang.second.base.modular.model.match.MatchResponse
import com.lintang.second.base.modular.model.match.SearchMatchResponse
import com.lintang.second.base.modular.util.Helper
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET(BuildConfig.LEAGUE_DETAIL)
    fun getLeagueDetail(@Query("id") idLeague: String): Observable<LeagueResponse>

    @GET(BuildConfig.NEXT_MATCH)
    fun getNextMatch(@Query("id") idLeague: String): Observable<MatchResponse>

    @GET(BuildConfig.PREV_MATCH)
    fun getPrevMatch(@Query("id") idLeague: String): Observable<MatchResponse>

    @GET(BuildConfig.SEARCH_MATCH)
    fun getSearchMatch(@Query("e") event: String): Observable<SearchMatchResponse>

    @GET(BuildConfig.MATCH_DETAIL)
    fun getMatchDetail(@Query("id") event: String): Observable<MatchResponse>


    companion object Factory {

        val getApiService: ApiService by lazy {
            val mLoggingInterceptor = HttpLoggingInterceptor()
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val cacheSize = (5 * 1024 * 1024).toLong()
            val appCache = Cache(BaseApplication.getContext().cacheDir, cacheSize)
            val mClient = if (BuildConfig.DEBUG) {
                OkHttpClient.Builder()
                    .cache(appCache)
                    .addInterceptor { chain ->

                        var request = chain.request()


                        request =
                            if (Helper.Func.isNetworkAvailable(BaseApplication.getContext())!!)

                                request.newBuilder().header(
                                    "Cache-Control",
                                    "public, max-age=" + 5
                                ).build()
                            else

                                request.newBuilder().header(
                                    "Cache-Control",
                                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                                ).build()

                        chain.proceed(request)
                    }
                    .addInterceptor(mLoggingInterceptor)
                    .addInterceptor(ChuckInterceptor(BaseApplication.getContext()))
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build()
            } else {
                OkHttpClient.Builder()
                    .cache(appCache)
                    .addInterceptor { chain ->
                        var request = chain.request()
                        request =
                            if (Helper.Func.isNetworkAvailable(BaseApplication.getContext())!!)
                                request.newBuilder().header(
                                    "Cache-Control",
                                    "public, max-age=" + 5
                                ).build()
                            else
                                request.newBuilder().header(
                                    "Cache-Control",
                                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                                ).build()
                        chain.proceed(request)
                    }
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build()
            }

            val mRetrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mClient)
                .build()

            mRetrofit.create(ApiService::class.java)
        }
    }
}