package com.example.shoppingcart.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.Item

@Database(entities = arrayOf(Item::class, Cart::class, Coupon::class), version = 1)
abstract class CartDatabase: RoomDatabase(){
    abstract fun ItemDao(): CartDao

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