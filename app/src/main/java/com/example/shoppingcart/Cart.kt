package com.example.shoppingcart

data class Cart (
    val cartID: Int,
    var products: ArrayList<Product>,
    var totalPrice: Int,
    var finalValue: Int,
    var coupon: Coupon
        )





