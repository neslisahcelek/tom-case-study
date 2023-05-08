package com.example.shoppingcart.model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class Product(
    @SerializedName("id")
    val productID: Int,
    @SerializedName("title")
    var productName: String,
    @SerializedName("price")
    var productPrice: Int,
    @SerializedName("description")
    var productDescription: String,
    @SerializedName("category")
    var categoryID: Category,
    @SerializedName("images")
    var productImage: ArrayList<String>
)
data class Category (
    val id: Int,
    var name: String,
    var image:String
)
data class Coupon(
    val couponID: Int,
    var discountType: String,
    var value: Int,
    var couponDescription: String
)
data class Item (
    val itemID: Int,
    var product: Product?,
    val cartID: Int,
    var quantity: Int,
    var subtotal: Int
)
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
    var date: Date?
)