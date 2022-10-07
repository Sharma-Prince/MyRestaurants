package com.pam.myrestaurants.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pam.myrestaurants.R


/***
 * For now we are using Local Image
 */
@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, s: String?) {
    val options = RequestOptions.placeholderOf(R.drawable.ic_restaurant).error(R.drawable.error)
    Glide.with(view).setDefaultRequestOptions(options).load(R.drawable.ic_restaurant).into(view)
}