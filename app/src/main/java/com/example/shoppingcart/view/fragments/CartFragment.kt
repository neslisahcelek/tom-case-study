package com.example.shoppingcart.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.*
import com.example.shoppingcart.adapters.CartProductAdapter
import com.example.shoppingcart.model.MockData


class CartFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_cart, container, false)

        val total:TextView = view.findViewById(R.id.textViewTotalPrice)
        total.text = "Total Price: ₺" + MockData.MockCart.cart.totalPrice.toString()

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewCart)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this.context,1,
            GridLayoutManager.VERTICAL, false)

        val adapter = CartProductAdapter(MockData.MockCart.cart.items) //all products in shopping cart from database
        recyclerView.adapter = adapter


        return view
    }


}