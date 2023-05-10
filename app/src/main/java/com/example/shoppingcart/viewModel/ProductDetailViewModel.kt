package com.example.shoppingcart.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.service.CartDatabase
import kotlinx.coroutines.launch
import java.util.*

class ProductDetailViewModel(application: Application): BaseViewModel(application){
    val productLiveData = MutableLiveData<Item>()

    fun getDataFromRoom(uuid: Int){
        var Item1:Item = Item("1","Item 1","https://picsum.photos/200/300",10.0,1,"technology","Item 1 description",
            Calendar.getInstance().time,
            Calendar.getInstance().time)
        productLiveData.value = Item1

        launch{
            val dao = CartDatabase(getApplication()).ItemDao()
            val product = dao.getItemById(uuid)
            productLiveData.value = product
        }
    }
}