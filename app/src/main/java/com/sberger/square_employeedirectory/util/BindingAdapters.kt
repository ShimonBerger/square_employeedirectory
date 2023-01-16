package com.sberger.square_employeedirectory.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.sberger.square_employeedirectory.R
import com.squareup.picasso.Picasso

// loads the image at given URL into the ImageView
@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    // shows image source. should only be red (network) the first time
    Picasso.get().setIndicatorsEnabled(true)

    Picasso.get()
        .load(url)
        .placeholder(R.drawable.outline_person)
        .error(R.drawable.outline_person)
        .resizeDimen(R.dimen.thumbnail, R.dimen.thumbnail)
        .centerCrop()
        .into(this)
}