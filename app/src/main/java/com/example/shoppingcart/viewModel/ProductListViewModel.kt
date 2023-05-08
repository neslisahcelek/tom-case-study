package com.example.shoppingcart.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        getDataFromAPI()
    }
    private fun getDataFromAPI(){
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