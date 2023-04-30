package com.example.shoppingcart

class Product(private val productID: Int,
              private var productName: String,
              private var productPrice:Int,
              private var categoryID: Int,
              var productQuantity: Int = 0) {
    fun getProductID(): Int {
        return productID
    }
    fun getProductName(): String {
        return productName
    }
    fun getProductPrice(): Int {
        return productPrice
    }
    fun getCategoryID(): Int {
        return categoryID
    }

}