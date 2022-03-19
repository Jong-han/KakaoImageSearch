package com.jh.kakaoimagesearch.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("thumbnail")
fun ImageView.setThumbnail(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}