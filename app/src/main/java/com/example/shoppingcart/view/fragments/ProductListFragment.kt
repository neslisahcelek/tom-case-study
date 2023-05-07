package com.example.shoppingcart.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.*
import com.example.shoppingcart.adapters.ProductAdapter
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.view.MainActivity

class ProductListFragment : Fragment() {
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

        var main: MainActivity = activity as MainActivity
        val adapter = main.databaseproducts?.let { ProductAdapter(it) } //all products from database
        recyclerView.adapter = adapter
        adapter?.notifyDataSetChanged()

        return view
    }




}