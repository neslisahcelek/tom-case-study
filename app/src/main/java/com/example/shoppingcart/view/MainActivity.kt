package com.example.shoppingcart.view

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.shoppingcart.R
import com.example.shoppingcart.databinding.ActivityMainBinding
import com.example.shoppingcart.model.Product
import com.example.shoppingcart.service.CartAPI
import com.example.shoppingcart.view.fragments.CartFragment
import com.example.shoppingcart.view.fragments.CouponsFragment
import com.example.shoppingcart.view.fragments.ProductListFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://fakestoreapi.com/"
     var databaseproducts : ArrayList<Product>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = resources.getColor(R.color.white)

        replaceFragment(ProductListFragment())

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
                    replaceFragment(ProductListFragment())
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

    private fun loadProducts(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CartAPI::class.java)
        val call = service.getProducts()

        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        databaseproducts = ArrayList(it)
                        Log.d(TAG, "onResponse: " + databaseproducts.toString())
                    }
                }
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d(TAG, "fail: " + t.message)
                t.printStackTrace()
            }
        })
    }
}