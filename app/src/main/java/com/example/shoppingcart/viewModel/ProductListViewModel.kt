package com.example.shoppingcart.viewModel

import android.app.Application
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.service.CartAPIService
import com.example.shoppingcart.service.CartDatabase
import com.example.shoppingcart.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductListViewModel(application: Application): BaseViewModel(application) {
    private val cartApiService = CartAPIService()
    private val disposable = CompositeDisposable()
    var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val products = MutableLiveData<List<Item>>() //MutableLiveData<Single<List<Product>>>()
    val productError = MutableLiveData<Boolean>()
    val productLoading = MutableLiveData<Boolean>()

    fun refreshData() {
/*      val updateTime=customSharedPreferences?.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {
            getDataFromAPI()
        }
 */
       products.value = MockData.MockItemList.ItemList//cartApiService.getProducts()//
        //getDataFromAPI()
    }

    private fun getDataFromSQLite() {
        productLoading.value = true
        launch {
            val products = CartDatabase(getApplication()).ItemDao().getAllItems()
            showProducts(products)
            Log.d(TAG, "getDataFromSQLite")
        }
    }
    private fun getDataFromAPI() {
        var cart:Cart = Cart("898998","88888", MockData.MockCart.cartItemList,MockData.MockCoupon.coupon1,0.0,0.0)
        disposable.add(
            cartApiService.createCart(cart)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response<Cart>>() {

                    override fun onSuccess(t: Response<Cart>) {
                        if (t.body() != null){
                            storeCartInSQLite(t.body()!!)
                            MockData.MockCart.cart = t.body()!!
                        }
                        Log.d(TAG, "getDataFromAPI" + (t.body()?.cartID ?:0))
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "hata" + e.message.toString())
                    }
                })
        )
     productLoading.value = true
        disposable.add(
            cartApiService.getItems()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Item>>() {
                    override fun onSuccess(t: List<Item>) {
                        storeInSQLite(t)
                        Log.d(TAG, "getDataFromAPI")
                    }

                    override fun onError(e: Throwable) {
                        productError.value = true
                        productLoading.value = false
                        Log.d(TAG, "error get data" + e.message.toString())

                    }
                })
        )
    }

    private fun showProducts(productList: List<Item>) {
        products.value = productList
        productError.value = false
        productLoading.value = false
    }
    private fun storeCartInSQLite(cart: Cart) {
        launch {
            val dao = CartDatabase(getApplication()).ItemDao()
            //dao.insertCart(cart)
            customSharedPreferences?.saveTime(System.nanoTime())
        }
        Log.println(Log.INFO, ContentValues.TAG, "CART"  )
    }

    private fun storeInSQLite(productList: List<Item>) {
        launch {
            val dao = CartDatabase(getApplication()).ItemDao()

            dao.deleteAllItems()

            val listLong = dao.insertAll(*productList.toTypedArray())
            var i = 0
            while (i < productList.size) {
                productList[i].uuid = listLong[i].toInt()
                i = i + 1
            }
            showProducts(productList)
        }

        customSharedPreferences?.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}