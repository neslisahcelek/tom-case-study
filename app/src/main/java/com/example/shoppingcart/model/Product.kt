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
    var categoryID: Int,
    @SerializedName("image")
    var productImage: Int
) {
    constructor(productID: Int, productName: String, productPrice: Int) : this(
        productID,
        productName,
        productPrice,
        "",
        0,
        0
    )

}




