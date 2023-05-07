package com.example.shoppingcart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Coupon
import com.example.shoppingcart.R
import com.example.shoppingcart.model.MockData

class CouponAdapter(private var coupons:ArrayList<Coupon>): RecyclerView.Adapter<CouponAdapter.CouponViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CouponViewHolder {
        val layoutView: View = LayoutInflater.from(parent.context).
        inflate(R.layout.coupon_card, parent, false)
        return CouponViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
        holder.couponDescription.text = coupons[position].couponDescription
        holder.couponButton.setOnClickListener {
            addCouponToCart(coupons[position], MockData.MockCart.cart)
        }
    }
    fun addCouponToCart(coupon: Coupon, shoppingCart: Cart) { //add product to shopping cart


    }
    override fun getItemCount(): Int {
        return coupons.size
    }

    class CouponViewHolder(view: View): RecyclerView.ViewHolder(view){
        var couponDescription: TextView = view.findViewById(R.id.textViewcouponDescription)
        var couponButton: Button = view.findViewById(R.id.buttonApplyCoupon)
    }
}
