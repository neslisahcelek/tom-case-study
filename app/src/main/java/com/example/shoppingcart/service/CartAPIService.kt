package com.example.shoppingcart.service

import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.Item
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body

class CartAPIService {
    private val BASE_URL = "http://13.53.130.220:8080/" //"http://16.171.2.137:8080/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(CartAPI::class.java)

    fun addItemToCart(Item:Item):Single<Response<List<Item>>>{
        return api.addItemToCart(Item)
    }
    fun createCart(request:Cart): Single<Response<Cart>>{
        return api.createCart(request)
    }
    fun getItems(): Single<List<Item>>{
        return api.getItems()
    }
    fun getCartItems():Single<Response<List<Item>>>{
        return api.getCartItems()
    }
    fun getCart():Cart{
        return api.getCart()
    }

    fun addCouponToCart(couponId: String):Single<Coupon>{
        return api.addExistingCouponToCart(couponId)
    }
    fun addNewCouponToCart(id:String,type:String,rate:Double,amount:Double):Single<Coupon>{
        return api.addNewCouponToCart(id,type,rate,amount)
    }
    fun getCoupons():Single<List<Coupon>>{
        return api.getCoupons()
    }
}