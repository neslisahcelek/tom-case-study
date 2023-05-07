package com.example.shoppingcart.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.adapters.CouponAdapter
import com.example.shoppingcart.R
import com.example.shoppingcart.model.MockData

class CouponsFragment : Fragment() {

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
        recyclerView.layoutManager = GridLayoutManager(this.context,1,
            GridLayoutManager.VERTICAL, false)

        val adapter = CouponAdapter(MockData.MockCoupon.couponList) //coupons from database
        recyclerView.adapter = adapter

        return view
    }

}