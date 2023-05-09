package com.example.shoppingcart.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.model.Product

@Dao
interface CartDao {
    @Insert
    suspend fun insertAll(vararg products: Product): List<Long>

    @Insert
    suspend fun insertAllItems(vararg items: Item): List<Long>

    @Insert
    suspend fun insertAllCoupons(vararg coupons: Coupon): List<Long>

    @Query("SELECT * FROM product")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT * FROM product WHERE uuid = :productId")
    suspend fun getProductById(productId: Int): Product

    @Query("DELETE FROM product")
    suspend fun deleteAllProducts()

    @Query("DELETE FROM product WHERE uuid = :productId")
    suspend fun deleteProductById(productId: Int)

    @Query("SELECT * FROM product WHERE category = :category")
    suspend fun getProductByCategory(category: String): List<Product>

    @Query("SELECT ITEMS FROM cart")
    suspend fun getItemsFromShoppingCart(): List<Item>

    @Query("SELECT * FROM coupon")
    suspend fun getAllCoupons(): List<Coupon>

}