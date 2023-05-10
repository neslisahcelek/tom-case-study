package com.example.shoppingcart.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.Item
import io.reactivex.Single
import retrofit2.Response

@Dao
interface CartDao {
    @Insert
    suspend fun insertCart(vararg cart: Response<Cart>): Single<Cart>
    @Insert
    suspend fun insertAll(vararg Items: Item): List<Long>

    @Insert
    suspend fun insertAllItems(vararg items: Item): List<Long>

    @Insert
    suspend fun insertAllCoupons(vararg coupons: Coupon): List<Long>

    @Query("SELECT * FROM Item")
    suspend fun getAllItems(): List<Item>

    @Query("SELECT * FROM Item WHERE uuid = :ItemId")
    suspend fun getItemById(ItemId: Int): Item

    @Query("DELETE FROM Item")
    suspend fun deleteAllItems()

    @Query("DELETE FROM Item WHERE uuid = :ItemId")
    suspend fun deleteItemById(ItemId: Int)

    @Query("SELECT * FROM Item WHERE category = :category")
    suspend fun getItemByCategory(category: String): List<Item>

    @Query("SELECT ITEMS FROM cart")
    suspend fun getItemsFromShoppingCart(): List<Item>

    @Query("SELECT * FROM coupon")
    suspend fun getAllCoupons(): List<Coupon>

    @Query("DELETE FROM coupon")
    suspend fun deleteAllCoupons()



}