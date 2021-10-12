package com.ricardo.muzmatchexercise.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ricardo.muzmatchexercise.R

@BindingAdapter(value = ["imageUrl"])
fun loadImage(
    imageView: ImageView,
    imageUrl: String?,
) {
    if (imageUrl != null && imageUrl.isNotEmpty()) {
        Glide
            .with(imageView)
            .load(imageUrl)
            .circleCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .into(imageView)
    }
}