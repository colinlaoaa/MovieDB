package com.liao.moviedb.view_model

import android.annotation.SuppressLint
import com.liao.moviedb.api.EndPoint
import com.liao.moviedb.model.PopularMovieList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieListRepository(private val endPoint: EndPoint) {


    fun getPopularMovieList() : Observable<PopularMovieList>{
        return endPoint.getPopularMovieList()

    }


}