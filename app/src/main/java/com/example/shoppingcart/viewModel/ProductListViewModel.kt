package com.example.shoppingcart.viewModel

import android.app.Application
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

class ProductListViewModel(application: Application): BaseViewModel(application) {
    private val cartApiService = CartAPIService()
    private val disposable = CompositeDisposable()
    var customSharedPreferences = CustomSharedPreferences(getApplication())

    val products = MutableLiveData<List<Product>>()
    val productError = MutableLiveData<Boolean>()
    val productLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        products.value = MockData.MockProductList.productList
        //getDataFromAPI()
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