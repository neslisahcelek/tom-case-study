package com.example.shoppingcart.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.Coupon
import com.example.shoppingcart.CouponAdapter
import com.example.shoppingcart.ProductAdapter
import com.example.shoppingcart.R

class CouponsFragment : Fragment() {
    val coupon1:Coupon = Coupon(1, "Coupon 1", 10,"")
    val coupon2:Coupon = Coupon(2, "Coupon 2", 20,"")
    val coupon3:Coupon = Coupon(3, "Coupon 3", 30,"")
    val coupon4:Coupon = Coupon(4, "Coupon 4", 40,"")
    val coupon5:Coupon = Coupon(5, "Coupon 5", 50,"")
    val coupon6:Coupon = Coupon(6, "Coupon 6", 60,"")

    val coupons:ArrayList<Coupon> = arrayListOf(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_item_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewItemList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this.context,2,
            GridLayoutManager.VERTICAL, false)
        val adapter = CouponAdapter(coupons)
        recyclerView.adapter = adapter
        return view
    }

}