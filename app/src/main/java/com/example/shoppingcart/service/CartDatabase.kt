package com.example.shoppingcart.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.model.Product

@Database(entities = arrayOf(Product::class, Cart::class, Coupon::class, Item::class), version = 1)
abstract class CartDatabase: RoomDatabase(){
    abstract fun productDao(): CartDao

    companion object{
        @Volatile private var INSTANCE: CartDatabase? = null

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(this){
            INSTANCE ?: makeDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, CartDatabase::class.java, "cartdatabase"
        ).build()
        }
    }