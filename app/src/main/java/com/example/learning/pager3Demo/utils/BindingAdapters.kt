package com.example.learning.pager3Demo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("load")
fun loadImage(view:ImageView,url:String?){
    url?.let {
        Glide.with(view).load(url).into(view)
    }
}