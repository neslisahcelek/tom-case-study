package com.example.shoppingcart.model

import com.example.shoppingcart.*
import com.example.shoppingcart.model.MockData.mockCategory.category1
import java.util.Calendar

object MockData {
 object MockProduct {
  val product1 = Product(1, "cheese", 80, "desc", category1, java.util.ArrayList<String>())
    val product2 = Product(2, "milk", 80, "desc", category1, java.util.ArrayList<String>())
      val product3 = Product(3, "bread", 80, "desc", category1, java.util.ArrayList<String>())
  val product4 = Product(4, "cheese", 80, "desc", category1, java.util.ArrayList<String>())
    val product5 = Product(5, "milk", 80, "desc", category1, java.util.ArrayList<String>())
      val product6 = Product(6, "bread", 80, "desc", category1, java.util.ArrayList<String>())
    val product7 = Product(7, "cheese", 80, "desc", category1, java.util.ArrayList<String>())
      val product8 = Product(8, "milk", 80, "desc", category1, java.util.ArrayList<String>())


 }
 object mockCategory{
  val category1 = Category(1, "techonology", "desc")
 }
 object MockCoupon {
  val coupon1: Coupon = Coupon(1, "Coupon 1", 10,"coupon 1")
  val coupon2: Coupon = Coupon(2, "Coupon 2", 20,"coupon 2")
  val coupon3: Coupon = Coupon(3, "Coupon 3", 30,"coupon 3")
  val coupon4: Coupon = Coupon(4, "Coupon 4", 40,"coupon 4")
  val coupon5: Coupon = Coupon(5, "Coupon 5", 50,"coupon 5")
  val coupon6: Coupon = Coupon(6, "Coupon 6", 60,"coupon 6")

  val couponList:ArrayList<Coupon> = arrayListOf(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6)

 }
 object MockCart {
  val cartProductList = arrayListOf<Item>()
  val cart = Cart(1,cartProductList, null ,0,0,1,Calendar.getInstance().time)

 }
 object MockItem{

 }
 object MockProductList{
    val productList = arrayListOf<Product>(
     MockProduct.product1,
     MockProduct.product2,
     MockProduct.product3,
     MockProduct.product4,
     MockProduct.product5,
     MockProduct.product6,
     MockProduct.product7,
     MockProduct.product8
    )
 }

}