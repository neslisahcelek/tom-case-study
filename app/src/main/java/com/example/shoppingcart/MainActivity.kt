package com.example.shoppingcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppingcart.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var shoppingCart = HashMap<Int, Product>()
    //var shoppingCart = ArrayList<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun addToCart(product: Product) { //add product to shopping cart
        if(shoppingCart.contains(product.getProductID())) {
            product.productQuantity++
        } else {
            shoppingCart.put(product.getProductID(),product)
        }
    }
}