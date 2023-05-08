package com.example.shoppingcart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList
@Entity(tableName = "product")
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
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
@Entity(tableName = "category")
data class Category (
    val id: Int,
    var name: String,
    var image:String
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
@Entity(tableName = "coupon")
data class Coupon(
    val couponID: Int,
    var discountType: String,
    var value: Int,
    var couponDescription: String
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
@Entity(tableName = "item")
data class Item (
    val itemID: Int,
    var product: Product?,
    val cartID: Int,
    var quantity: Int,
    var subtotal: Int
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
@Entity(tableName = "cart")
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
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}