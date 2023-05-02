package com.example.shoppingcart

data class Product(
    val productID: Int,
    var productName: String,
    var productPrice: Int,
    var productDescription: String,
    var categoryID: Int,
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




