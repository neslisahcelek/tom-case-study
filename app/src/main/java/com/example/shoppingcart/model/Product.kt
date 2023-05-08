package com.example.shoppingcart.model

import com.google.gson.annotations.SerializedName

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




