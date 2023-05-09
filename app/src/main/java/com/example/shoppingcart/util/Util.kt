package com.example.shoppingcart.util

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.shoppingcart.R
import com.example.shoppingcart.model.Product

fun ImageView.downloadFromUrl(url: String?,progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.shopping_cart_48px)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}
fun placeHolderProgressBar(context:Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}
