package com.example.shoppingcart.service

import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.model.Product
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CartAPI {
    @GET("/products")  //Observable
    fun getProducts(): Single<List<Product>> //Call<List<Product>>

    @GET("/cart")
    fun getCartItems(): Single<List<Item>>

    @GET("/coupon")
    fun getCoupons(): Single<List<Coupon>>
}