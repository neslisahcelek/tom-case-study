package com.example.shoppingcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.shoppingcart.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    var shoppingCart = HashMap<Int, Int>()
    //var shoppingCart = ArrayList<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(ItemListFragment())

        binding.buttomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.couponsFragment -> {
                    replaceFragment(CouponsFragment())
                    true
                }
                R.id.cartFragment -> {
                    replaceFragment(CartFragment())
                    true
                }
                R.id.itemListFragment -> {
                    replaceFragment(ItemListFragment())
                    true
                }
                else -> false
            }
        }
    }

    fun replaceFragment(fragment: Fragment){
        val fragmnetManager = supportFragmentManager
        val fragmentTransaction = fragmnetManager.beginTransaction()
        fragmentTransaction.replace(binding.frameLayout.id, fragment)
        fragmentTransaction.commit()
    }

    fun addToCart(product: Product) { //add product to shopping cart
        if(shoppingCart.containsKey(product.getProductID())) {
            var newQuantity = product.productQuantity++
            shoppingCart.put(product.getProductID(),newQuantity)
            println("Product quantity increased to ${product.productQuantity}")

        } else {
            shoppingCart.put(product.getProductID(),1)
            println("Product added to cart")
        }
    }
}