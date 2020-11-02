package com.test.cleanArchRoomTest.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.test.cleanArchRoomTest.R

@BindingAdapter(value = ["imageUrl"])
fun ImageView.LoadImageUrl(imageUrl: String?) {
    Glide.with(this)
        .load(imageUrl)
        .error(R.drawable.ic_baseline_adb_24)
        .into(this)
}