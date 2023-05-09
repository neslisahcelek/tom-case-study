package com.example.shoppingcart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList
@Entity(tableName = "product")
data class Product(
    @SerializedName("id")
    val productID: Int,
    @SerializedName("title")
    var productName: String,
    @SerializedName("price")
    var productPrice: Double,
    @SerializedName("description")
    var productDescription: String,
    @SerializedName("category")
    var categoryID: Category,
    @SerializedName("image")
    var productImage: String
) : Serializable {
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
    var subtotal: Double = quantity * (product?.productPrice ?: 0.0)
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
    var totalPrice: Double= 0.0,
    @SerializedName("finalValue")
    var finalPrice: Double=0.0,
    @SerializedName("userId")
    var userID:Int?,
    @SerializedName("date")
    var date: Date?
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
    init {
        calculateTotalPrice()
    }
    private fun calculateTotalPrice() {
        var totalPrice = 0.0
        for (item in items) {
            totalPrice += item.subtotal
        }
        this.totalPrice = totalPrice
    }

}