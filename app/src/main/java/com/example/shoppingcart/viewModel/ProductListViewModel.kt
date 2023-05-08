package com.example.shoppingcart.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.model.Product
import com.example.shoppingcart.service.CartAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ProductListViewModel: ViewModel(){
    private val cartApiService = CartAPIService()
    private val disposable = CompositeDisposable()
    val products = MutableLiveData<List<Product>>()
    val productError = MutableLiveData<Boolean>()
    val productLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val product1 = Product(1, "cheese", 80, "desc", MockData.mockCategory.category1, java.util.ArrayList<String>())
        val product2 = Product(2, "milk", 80, "desc", MockData.mockCategory.category1, java.util.ArrayList<String>())
        val product3 = Product(3, "bread", 80, "desc", MockData.mockCategory.category1, java.util.ArrayList<String>())
        val product4 = Product(4, "cheese", 80, "desc", MockData.mockCategory.category1, java.util.ArrayList<String>())
        val product5 = Product(5, "milk", 80, "desc", MockData.mockCategory.category1, java.util.ArrayList<String>())
        val product6 = Product(6, "bread", 80, "desc", MockData.mockCategory.category1, java.util.ArrayList<String>())
        val product7 = Product(7, "cheese", 80, "desc", MockData.mockCategory.category1, java.util.ArrayList<String>())
        val product8 = Product(8, "milk", 80, "desc", MockData.mockCategory.category1, java.util.ArrayList<String>())
        products.value = arrayListOf(product1, product2, product3, product4, product5, product6, product7, product8)
        //getDataFromAPI()
    }
    private fun getDataFromAPI(){
        productLoading.value = true

        disposable.add(
            cartApiService.getProducts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<List<Product>>(){
                    override fun onSuccess(t: List<Product>) {
                        products.value = t
                        productError.value = false
                        productLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        productError.value = true
                        productLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }
}