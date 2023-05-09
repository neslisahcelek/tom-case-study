package com.example.shoppingcart.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.model.Product
import com.example.shoppingcart.service.CartAPIService
import com.example.shoppingcart.service.CartDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.UUID

class ProductDetailViewModel(application: Application): BaseViewModel(application){
    val productLiveData = MutableLiveData<Product>()

    fun getDataFromRoom(uuid: Int){
        val product1 = Product(1, "cheese", 80.0, "desc", MockData.mockCategory.category1, MockData.MockProduct.image)
        productLiveData.value = product1

        launch{
            val dao = CartDatabase(getApplication()).productDao()
            val product = dao.getProductById(uuid)
            productLiveData.value = product
        }
    }
}