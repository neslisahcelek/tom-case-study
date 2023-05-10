package com.example.shoppingcart.viewModel

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.service.CartAPIService
import com.example.shoppingcart.service.CartDatabase
import com.example.shoppingcart.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class CouponsViewModel(application: Application): BaseViewModel(application){
    private val cartApiService = CartAPIService()
    private val disposable = CompositeDisposable()
    var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val coupons = MutableLiveData<List<Coupon>>() //MutableLiveData<Single<List<Coupon>>>()
    val couponsError = MutableLiveData<Boolean>()
    val couponsLoading = MutableLiveData<Boolean>()
    fun refreshData() {
/*      val updateTime=customSharedPreferences?.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {
            getDataFromAPI()
        }
 */
        coupons.value = MockData.MockCoupon.couponList //cartApiService.getProducts()//
    }
    fun refreshFromAPI() {

    }
    private fun getDataFromSQLite() {
        couponsLoading.value = true
        launch {
            val coupons = CartDatabase(getApplication()).ItemDao().getAllCoupons()
            showCoupons(coupons)
        }
    }
    private fun getDataFromAPI() {
        couponsLoading.value = true

        disposable.add(
            cartApiService.getCoupons()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Coupon>>() {
                    override fun onSuccess(t: List<Coupon>) {
                        storeInSQLite(t)
                        Log.d(ContentValues.TAG, "getcouponsFromAPI")
                    }

                    override fun onError(e: Throwable) {
                        couponsError.value = true
                        couponsLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }
    private fun showCoupons(couponList: List<Coupon>) {
        coupons.value = couponList
        couponsError.value = false
        couponsLoading.value = false
    }

    private fun storeInSQLite(couponList: List<Coupon>) {
        launch {
            val dao = CartDatabase(getApplication()).ItemDao()
            dao.deleteAllCoupons()

            val listLong = dao.insertAllCoupons(*couponList.toTypedArray())
            var i = 0
            while (i < couponList.size) {
                couponList[i].uuid = listLong[i].toInt()
                i = i + 1
            }
            showCoupons(couponList)
        }

        customSharedPreferences?.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}