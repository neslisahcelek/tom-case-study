package com.example.shoppingcart.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList
@Entity(tableName = "item")
data class Item(
    @SerializedName("id")
    val ItemID: String,
    @SerializedName("name")
    var ItemName: String,
    @SerializedName("url")
    var ItemImage: String,
    @SerializedName("price")
    var ItemPrice: Double,
    @SerializedName("quantity")
    var quantity: Int,
    @SerializedName("category")
    var category: String,
    @SerializedName("description")
    var ItemDescription: String,
    @SerializedName("createDate")
    var createDate: Date?,
    @SerializedName("changeDate")
    var changeDate: Date?

) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
@Entity(tableName = "coupon")
data class Coupon (
    val id: String,
    var type: Int,
    var rate: Double,
    var amount:Double,
    var description:String
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

@Entity(tableName = "cart")
data class Cart(
    @SerializedName("id")
    val cartID: String?,
    val customerId:String,
    @SerializedName("items")
    var items: ArrayList<Item>?,
    var coupon: Coupon?,
    var totalPrice: Double?,
    @SerializedName("discountedPrice")
    var finalPrice: Double?
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
    init {/*
        calculateTotalPrice()
        calculateFinalPrice()
        */
    }

    private fun calculateFinalPrice() {
        this.finalPrice = totalPrice
        if (coupon != null) {
                if (coupon!!.type == 1) { //ratio
                    finalPrice = totalPrice!! * (1 - coupon!!.rate)
                } else if (coupon!!.type == 2) {
                    finalPrice = totalPrice!! - coupon!!.amount
                }
            this.finalPrice = finalPrice
        }
    }

    private fun calculateTotalPrice() {
        for (item in items!!) {
            totalPrice = totalPrice?.plus(item.ItemPrice*item.quantity)
        }
        this.totalPrice = totalPrice
    }
}