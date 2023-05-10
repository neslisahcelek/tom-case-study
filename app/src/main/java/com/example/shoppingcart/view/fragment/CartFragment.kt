package com.example.shoppingcart.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.example.shoppingcart.adapter.CartProductAdapter
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.service.CartAPIService


class CartFragment : Fragment() {
    private val cartApiService = CartAPIService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar?.setTitle("Cart")
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_cart, container, false)


        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewCart)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this.context,1,
            GridLayoutManager.VERTICAL, false)

        val adapter =
            MockData.MockCart.cart.items?.let { CartProductAdapter(it) } //all products in shopping cart from database
        recyclerView.adapter = adapter


        var total: TextView = view.findViewById(R.id.textViewTotal)
        total.text =  "Total: ₺" + MockData.MockCart.cart.totalPrice
        //total.text = "Total Price: ₺" + cartApiService.getCart().totalPrice.toString(

        var discount: TextView = view.findViewById(R.id.textViewDiscount)
        if (MockData.MockCart.cart.coupon == null){
            discount.text = "Discount: ₺0"
        }
        else{
            discount.text = "Discount: ₺" + MockData.MockCart.cart.coupon?.amount.toString()
            //cartApiService.getCart().coupon.value.toString()
        }

        var finalPrice: TextView = view.findViewById(R.id.textViewFinalPrice)
        finalPrice.text = "Final Price: ₺" + MockData.MockCart.cart.finalPrice

        return view
    }


}