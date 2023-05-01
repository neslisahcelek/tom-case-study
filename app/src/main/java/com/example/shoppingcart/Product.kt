package com.example.shoppingcart

data class Product(
    val productID: Int,
    var productName: String,
    var productPrice: Int,
    var productDescription: String,
    var categoryID: Int,
    var productQuantity: Int = 0
)




