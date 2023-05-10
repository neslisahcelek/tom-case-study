package com.example.shoppingcart.view

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.LogPrinter
import androidx.fragment.app.Fragment
import com.example.shoppingcart.R
import com.example.shoppingcart.databinding.ActivityMainBinding
import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.service.CartAPIService
import com.example.shoppingcart.service.CartDatabase
import com.example.shoppingcart.util.CustomSharedPreferences
import com.example.shoppingcart.view.fragment.CartFragment
import com.example.shoppingcart.view.fragment.CouponsFragment
import com.example.shoppingcart.view.fragment.ProductListFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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


}
/*  private fun loadProducts(){
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

 */