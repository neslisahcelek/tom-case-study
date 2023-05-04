package com.example.shoppingcart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.shoppingcart.databinding.ActivityMainBinding
import com.example.shoppingcart.fragments.CartFragment
import com.example.shoppingcart.fragments.CouponsFragment
import com.example.shoppingcart.fragments.ItemListFragment
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = resources.getColor(R.color.white)

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
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.frameLayout.id, fragment)
        fragmentTransaction.commit()
    }


}