package com.example.shoppingcart.model

data class Coupon(
    val couponID: Int,
    var discountType: String,
    var value: Int,
    var couponDescription: String
)

