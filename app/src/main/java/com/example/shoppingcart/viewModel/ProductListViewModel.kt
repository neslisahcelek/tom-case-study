package com.example.shoppingcart.viewModel

import android.app.Application
import android.content.ContentValues.TAG
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.model.Product
import com.example.shoppingcart.service.CartAPIService
import com.example.shoppingcart.service.CartDatabase
import com.example.shoppingcart.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import android.content.SharedPreferences
import android.util.Log
import com.example.shoppingcart.service.CartAPI
import io.reactivex.Single
import kotlin.math.log

class ProductListViewModel(application: Application): BaseViewModel(application) {
    private val cartApiService = CartAPIService()
    private val disposable = CompositeDisposable()
    var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val products = MutableLiveData<List<Product>>() //MutableLiveData<Single<List<Product>>>()
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
       products.value = MockData.MockProductList.productList //cartApiService.getProducts()//
    }
    fun refreshFromAPI() {
        getDataFromAPI()
    }
    private fun getDataFromSQLite() {
        productLoading.value = true
        launch {
            val products = CartDatabase(getApplication()).productDao().getAllProducts()
            showProducts(products)
            Log.d(TAG, "getDataFromSQLite")
        }
    }
    private fun getDataFromAPI() {
        productLoading.value = true

        disposable.add(
            cartApiService.getProducts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Product>>() {
                    override fun onSuccess(t: List<Product>) {
                        storeInSQLite(t)
                        Log.d(TAG, "getDataFromAPI")
                    }

                    override fun onError(e: Throwable) {
                        productError.value = true
                        productLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun showProducts(productList: List<Product>) {
        products.value = productList
        productError.value = false
        productLoading.value = false
    }

    private fun storeInSQLite(productList: List<Product>) {
        launch {
            val dao = CartDatabase(getApplication()).productDao()

            dao.deleteAllProducts()

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