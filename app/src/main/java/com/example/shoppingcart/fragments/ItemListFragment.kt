package com.example.shoppingcart.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.*

class ItemListFragment : Fragment() {

    val product1 = Product(1, "Product 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
    val product2 = Product(1, "Product 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
    val product3 = Product(3, "Product 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
    val product4 = Product(4, " 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
    val product5 = Product(5, "Product 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
    val product6 = Product(6, "Prot 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
    val product8 = Product(7, "Product 1", 10, "desc", 12, R.drawable.shopping_cart_48px)
    val product7 = Product(8, "Product 1", 1000, "desc", 12, R.drawable.shopping_cart_48px)



    val cart = Cart(1, arrayListOf(),0,0, Coupon(1,"",0,""))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_item_list, container, false)

        val recyclerView:RecyclerView = view.findViewById(R.id.recyclerViewItemList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this.context,2,
            GridLayoutManager.VERTICAL, false)

        cart.products.add(product1)
        cart.products.add(product2)

        cart.products.add(product3)
        cart.products.add(  product4)
        cart.products.add(product5)
        cart.products.add(product6)
        cart.products.add(product7)
        cart.products.add(product8)

        val adapter = ProductAdapter(cart.products)
        recyclerView.adapter = adapter


        return view
    }




}