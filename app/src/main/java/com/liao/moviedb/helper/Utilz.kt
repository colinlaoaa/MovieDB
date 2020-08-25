package com.liao.moviedb.helper

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.liao.moviedb.R
import com.liao.moviedb.app.Config
import kotlinx.android.synthetic.main.app_bar.*

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?){
    var picUrl = Config.PIC_BASE_URL+url
    Glide.with(this)
        .load(picUrl)
        .placeholder(R.drawable.wait)
        .into(this)
}

fun AppCompatActivity.toolbar(title: String){
    var toolbar = this.toolbar
    toolbar.title = title
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
}

