package com.example.shoppingcart.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Cart(
    val cartID: Int,
    @SerializedName("products")
    var items: ArrayList<Item>,
    var coupon: Coupon?,
    var totalPrice: Int=0,
    var finalPrice: Int=0,
    @SerializedName("userId")
    var userID:Int?,
    @SerializedName("date")
    var date:Date?
        )





