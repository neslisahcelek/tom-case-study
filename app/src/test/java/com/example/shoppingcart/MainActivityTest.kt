package com.example.shoppingcart

import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.Product
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class MainActivityTest {

    @Before
    fun setUp() {
    }

    @Test
    open fun testAddToCartaNewProduct() {
        val product = Product(123, "Test Product", 10.0.toInt())

        val cart = Cart(1, HashMap<Int, Int>(), 0, 0, Coupon(1, "Test Coupon", 10.0.toInt()))

        addToCart(product,cart)

        // check if the cart contains the product
        assertTrue(cart.products.containsKey(product.productID))
        assertEquals(cart.products.get(product.productID), 1)
    }
    @Test
    open fun testIncreaseProductQuantityInCart() {
        val product = Product(123, "Test Product", 10.0.toInt())
        val product2 = Product(123, "Test Product2", 10.2.toInt())

        val cart = Cart(1, HashMap<Int, Int>(), 0, 0, Coupon(1, "Test Coupon", 10.0.toInt()))

        addToCart(product,cart)
        addToCart(product2,cart)

        // check if the product quantity increased
        assertEquals(cart.products.get(product.productID), 2)
    }

}