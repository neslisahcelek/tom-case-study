package com.example.shoppingcart

data class Cart(
    val cartID: Int,
    var items: ArrayList<Item>,
    var coupon: Coupon?,
    var totalPrice: Int=0,
    var finalPrice: Int=0
        )





