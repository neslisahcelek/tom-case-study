package com.example.shoppingcart.service

import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.model.Product
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CartAPIService {
    private val BASE_URL = "https://api.escuelajs.co/api/v1/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(CartAPI::class.java)

    fun getProducts():Single<List<Product>>{
        return api.getProducts()
    }
    fun getCartItems():Single<List<Item>>{
        return api.getCartItems()
    }
    fun getCart():Cart{
        return api.getCart()
    }
    fun addItemToCart(item: Item):Single<Item>{
        return api.addItemToCart(item)
    }
    fun addProductToCart(product:Product):Single<Product>{
        return api.addProductToCart(product)
    }
    fun addCouponToCart(coupon: Coupon):Single<Coupon>{
        return api.addCouponToCart(coupon)
    }

    fun getCoupons():Single<List<Coupon>>{
        return api.getCoupons()
    }

    fun changeQuantity(currentItem: Item) {

    }
}