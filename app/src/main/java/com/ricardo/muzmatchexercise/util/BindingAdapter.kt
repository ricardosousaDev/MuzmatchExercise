package com.ricardo.muzmatchexercise.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["imageUrl"])
fun loadImage(
    imageView: ImageView,
    imageUrl: String?,
) {
    if (imageUrl != null && imageUrl.isNotEmpty()) {
        Picasso.get().load(imageUrl).transform(PicassoCircleTransformation()).into(imageView)
    }
}