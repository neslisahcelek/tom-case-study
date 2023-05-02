package com.example.shoppingcart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private var products:ArrayList<Product>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val layoutView:View = LayoutInflater.from(parent.context).
        inflate(R.layout.product_card, parent, false)
        return ProductViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productImage.setImageResource(products[position].productImage)
        holder.productTitle.text = products[position].productName
        holder.productPrice.text = "₺" + products[position].productPrice.toString()
        holder.productButton.setOnClickListener {
            addToCart(products[position], Cart(1, arrayListOf(),0,0, Coupon(1,"",0,"desc")))
        }
    }
    fun addToCart(product: Product, shoppingCart:Cart) { //add product to shopping cart
        if (shoppingCart.products.contains(product)) {
            shoppingCart.products.add(product)
///////////////////////////

        } else {
            shoppingCart.products.add(product)
            println("Product added to cart" + product.productID)
        }
    }
    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        var productImage: ImageView = view.findViewById(R.id.imageViewProduct)
        var productTitle: TextView = view.findViewById(R.id.textViewProductTitle)
        var productPrice: TextView = view.findViewById(R.id.textViewProductPrice)
        var productButton: Button = view.findViewById(R.id.buttonAddToCart)
    }
}