package com.liao.moviedb.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liao.moviedb.model.MovieDetail
import com.liao.moviedb.model.PopularMovieList
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieListViewModel(var movieListRepository: MovieListRepository) : ViewModel() {
    private val movieListResponse: MutableLiveData<List<MovieDetail>> by lazy {
        MutableLiveData<List<MovieDetail>>()
    }

    fun getMovieList() {
        val observer = object : Observer<PopularMovieList> {
            override fun onNext(t: PopularMovieList) {
                movieListResponse.postValue(t.results)
            }

            override fun onError(e: Throwable) {
                Log.d("MYTAG", "onError")
                Log.d("MYTAG", e.message.toString())
            }

            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }
        }
        var observable = movieListRepository.getPopularMovieList()
        observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(observer)
    }

    fun movieListObserver() = movieListResponse




}

class ViewModelFactory(private var movieListRepository: MovieListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java))
            return MovieListViewModel(movieListRepository) as T
        throw IllegalArgumentException("It is only create MainViewModel object")
    }


}