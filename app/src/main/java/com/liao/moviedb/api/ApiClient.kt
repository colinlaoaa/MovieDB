package com.liao.moviedb.api

import com.liao.moviedb.app.Config
import com.liao.moviedb.app.EndPoints
import com.liao.moviedb.model.PopularMovieList
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {
    private val _endpoint: EndPoint by lazy {
        val client = Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        client.create(EndPoint::class.java)
    }

    fun getApiEndPoint(): EndPoint {
        return _endpoint
    }

}


interface EndPoint {

    @GET(EndPoints.POPULAR_MOVIES)
    fun getPopularMovieList(
    ): Observable<PopularMovieList>

}