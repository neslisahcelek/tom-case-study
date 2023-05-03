package com.example.shoppingcart.mock

import com.example.shoppingcart.Cart
import com.example.shoppingcart.Coupon
import com.example.shoppingcart.Product
import com.example.shoppingcart.R

object MockData {
 object MockProduct {
  val product1 = Product(1, "Product 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
  val product2 = Product(1, "Product 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
  val product3 = Product(3, "Product 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
  val product4 = Product(4, " 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
  val product5 = Product(5, "Product 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
  val product6 = Product(6, "Prot 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
  val product8 = Product(7, "Product 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
  val product7 = Product(8, "Product 1", 1000, "desc", 12, R.drawable.shopping_cart_48px)

 }
 object MockCoupon {
  val coupon1:Coupon = Coupon(1, "Coupon 1", 10,"coupon 1")
  val coupon2:Coupon = Coupon(2, "Coupon 2", 20,"coupon 2")
  val coupon3:Coupon = Coupon(3, "Coupon 3", 30,"coupon 3")
  val coupon4:Coupon = Coupon(4, "Coupon 4", 40,"coupon 4")
  val coupon5:Coupon = Coupon(5, "Coupon 5", 50,"coupon 5")
  val coupon6:Coupon = Coupon(6, "Coupon 6", 60,"coupon 6")

  val couponList:ArrayList<Coupon> = arrayListOf(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6)

 }
 object MockCart {
  val cart = Cart(1, arrayListOf(),0,0, Coupon(1,"",0,""))
  val CartProductList = arrayListOf<Product>(
   MockProduct.product4,
   MockProduct.product5,
   MockProduct.product7,
   MockProduct.product8
  )
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