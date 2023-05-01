package com.example.shoppingcart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter():RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        val layoutView:View = LayoutInflater.from(parent.context).
        inflate(R.layout.product_card, parent, false)
        return ProductViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 4
    }

    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        var productImage: ImageView = view.findViewById(R.id.imageViewProduct)
        var productTitle: TextView = view.findViewById(R.id.textViewProductTitle)
        var productPrice: TextView = view.findViewById(R.id.textViewProductPrice)
    }
}