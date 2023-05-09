package com.example.shoppingcart.viewModel

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.model.Product
import com.example.shoppingcart.service.CartAPIService
import com.example.shoppingcart.service.CartDatabase
import com.example.shoppingcart.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class CartViewModel(application: Application): BaseViewModel(application) {
    private val cartApiService = CartAPIService()
    private val disposable = CompositeDisposable()
    var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L


    val items = MutableLiveData<List<Item>>()

    fun refreshData() {
/*      val updateTime=customSharedPreferences?.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {
            getDataFromAPI()
        }
 */
        items.value = MockData.MockCart.cartProductList //cartApiService.getCartItems(
    }
    fun refreshFromAPI() {
        getDataFromAPI()
    }
    private fun getDataFromSQLite() {
        launch {
            val items = CartDatabase(getApplication()).productDao().getItemsFromShoppingCart()
            showItems(items)
            Log.d(ContentValues.TAG, "getitemsFromSQLite")
        }
    }
    private fun getDataFromAPI() {
        disposable.add(
            cartApiService.getCartItems()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Item>>() {
                    override fun onSuccess(t: List<Item>) {
                        storeInSQLite(t)
                        Log.d(ContentValues.TAG, "getitemsFromAPI")
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun showItems(itemList: List<Item>) {
        items.value = itemList
    }
    private fun storeInSQLite(itemList: List<Item>) {
        launch {
            val dao = CartDatabase(getApplication()).productDao()

            val listLong = dao.insertAllItems(*itemList.toTypedArray())
            var i = 0
            while (i < itemList.size) {
                itemList[i].uuid = listLong[i].toInt()
                i = i + 1
            }
            showItems(itemList)
        }

        customSharedPreferences?.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}