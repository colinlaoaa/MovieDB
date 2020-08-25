package com.liao.moviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.liao.moviedb.databinding.NewRowPopularBinding
import com.liao.moviedb.model.MovieDetail
import com.liao.moviedb.view_model.MovieListViewModel
import kotlinx.android.synthetic.main.mycustomview.view.*
import kotlinx.android.synthetic.main.new_row_popular.view.*


class AdapterMovieList ( private var viewModel: MovieListViewModel) :
    RecyclerView.Adapter<AdapterMovieList.MyViewHolder>() {

    private var listItem = ArrayList<MovieDetail>()
    fun setData(list: List<MovieDetail>) {
        listItem.clear()
        listItem.addAll(list)
        notifyDataSetChanged()

    }

    inner class MyViewHolder(private val binding: NewRowPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieDetail) {
            binding.viewModel = viewModel
            binding.item = item
            binding.adapter = this@AdapterMovieList
            binding.executePendingBindings()
            binding.root.overview.textView_overView.text = item.overview


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding =NewRowPopularBinding.inflate(LayoutInflater.from(parent.context))
        var layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        binding.root.layoutParams = layoutParams
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }


}