package com.example.shoppingcart.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.model.Product
import com.example.shoppingcart.service.CartAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ProductDetailViewModel: ViewModel(){
    private val cartApiService = CartAPIService()
    private val disposable = CompositeDisposable()
    val productLiveData = MutableLiveData<Product>()

    fun getDataFromRoom(){
        val product1 = Product(1, "cheese", 80, "desc", MockData.mockCategory.category1, java.util.ArrayList<String>())
        productLiveData.value = product1
    }
}