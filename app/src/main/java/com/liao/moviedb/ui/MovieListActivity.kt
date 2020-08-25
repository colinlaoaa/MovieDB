package com.liao.moviedb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.liao.moviedb.R
import com.liao.moviedb.adapter.AdapterMovieList
import com.liao.moviedb.api.ApiClient
import com.liao.moviedb.databinding.ActivityMovieListBinding
import com.liao.moviedb.helper.toolbar
import com.liao.moviedb.view_model.MovieListRepository
import com.liao.moviedb.view_model.MovieListViewModel
import com.liao.moviedb.view_model.ViewModelFactory

class MovieListActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieListBinding
    lateinit var viewModel: MovieListViewModel
    lateinit var mAdapter: AdapterMovieList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie_list)
        viewModel = ViewModelProvider(this, ViewModelFactory(MovieListRepository(ApiClient.getApiEndPoint()))).get(MovieListViewModel::class.java)
        toolbar("Movie List")
        this.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mAdapter = AdapterMovieList(viewModel)

        init()
        observerData()

    }

    private fun observerData() {
       viewModel.movieListObserver().observe(this, Observer {
           binding.refreshLayout.isRefreshing = false
           if(it != null){
               mAdapter.setData(it)
           }
       })
    }

    private fun init() {
        viewModel.getMovieList()
        binding.recyclerViewMovie.apply {
            adapter = mAdapter
        }

        binding.refreshLayout.setOnRefreshListener {
            viewModel.getMovieList()
        }
    }
}