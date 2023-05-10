package com.example.shoppingcart.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.service.CartAPIService
import com.example.shoppingcart.view.fragment.CartFragment
import com.example.shoppingcart.view.fragment.CouponsFragment

class CouponAdapter(private var coupons:ArrayList<Coupon>): RecyclerView.Adapter<CouponAdapter.CouponViewHolder>(){
    private val cartApiService = CartAPIService()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CouponViewHolder {
        val layoutView: View = LayoutInflater.from(parent.context).
        inflate(R.layout.coupon_card, parent, false)
        return CouponViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
        holder.couponDescription.text = coupons[position].description
        holder.couponButton.setOnClickListener {
            addCouponToCart(coupons[position], MockData.MockCart.cart)
            //cartApiService.addCouponToCart(coupons[position])
        }
    }
    fun addCouponToCart(coupon: Coupon, shoppingCart: Cart) { //add product to shopping cart
        if (shoppingCart.items?.isEmpty() == true){
            /*val alert = AlertDialog.Builder()
            alert.setMessage("Cart is empty, coupon can not applied.")

             */
            return
        }
        shoppingCart.coupon = coupon
        var total = shoppingCart.totalPrice
        var final: Double? = total
        if (total != null) {
            final = total - coupon.amount
        }
        shoppingCart.finalPrice = final
    }
    override fun getItemCount(): Int {
        return coupons.size
    }

    class CouponViewHolder(view: View): RecyclerView.ViewHolder(view){
        var couponDescription: TextView = view.findViewById(R.id.textViewcouponDescription)
        var couponButton: Button = view.findViewById(R.id.buttonApplyCoupon)
    }
    fun updateCouponList(newCouponList: List<Coupon>) {
        coupons.clear()
        coupons.addAll(newCouponList)
        notifyDataSetChanged()
    }
}
