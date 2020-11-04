package com.test.cleanArchRoomTest.utils.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.test.cleanArchRoomTest.R

@BindingAdapter(value = ["imageUrl", "placeHolder"], requireAll = false)
fun ImageView.LoadImageUrl(imageUrl: String?, placeholder: Drawable) {
    Glide.with(this)
        .load(imageUrl)
        .placeholder(placeholder)
        .error(R.drawable.ic_baseline_adb_24)
        .into(this)
}