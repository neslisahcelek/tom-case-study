package com.example.shoppingcart.service

import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.model.Product
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface CartAPI {
    @GET("/products")  //Observable
    fun getProducts(): Single<List<Product>> //Call<List<Product>>

    @GET("/cart/1/items")
    fun getCartItems(): Single<List<Item>>
    @POST("/cart")
    fun addItemToCart(item: Item): Single<Item>
    @POST("/cart")
    fun addProductToCart(product:Product): Single<Product>
    @POST("/cart/1/coupons")
    fun addCouponToCart(coupon: Coupon): Single<Coupon>
    @GET("/coupon")
    fun getCoupons(): Single<List<Coupon>>

    @GET("/cart")
    fun getCart(): Cart
}