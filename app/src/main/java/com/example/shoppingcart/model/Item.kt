package com.example.shoppingcart.model

data class Item (
    val itemID: Int,
    var product: Product?,
    val cartID: Int,
    var quantity: Int,
    var subtotal: Int
)