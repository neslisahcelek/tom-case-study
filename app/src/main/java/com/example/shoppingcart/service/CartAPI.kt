package com.example.shoppingcart.service

import com.example.shoppingcart.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface CartAPI {
    @GET("products")
    fun getProducts(): Call<List<Product>>
}