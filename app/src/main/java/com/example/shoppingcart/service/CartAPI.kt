package com.example.shoppingcart.service

import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.Item
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CartAPI {
    @GET("/v1/shopping-carts/1/items")
    fun getCartItems(): Single<Response<List<Item>>>
    @POST("/v1/shopping-carts/1/items")
    fun addItemToCart(@Body item:Item): Single<Response<List<Item>>>
    @POST("/v1/shopping-carts/")
    fun createCart(@Body request:Cart): Single<Response<Cart>>
    fun getItems(): Single<List<Item>>
    @POST("/v1/shopping-carts/{id}/coupons")
    fun addExistingCouponToCart(@Body id:String): Single<Coupon>
    @POST("/v1/shopping-carts/{id}/coupons")
    fun addNewCouponToCart(@Body id:String,type:String,rate:Double,amount:Double): Single<Coupon>
    @GET("/coupon")
    fun getCoupons(): Single<List<Coupon>>

    @GET("/v1/shopping-carts/820056")
    fun getCart(): Cart
}