package com.example.shoppingcart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.Cart
import com.example.shoppingcart.Coupon
import com.example.shoppingcart.Product
import com.example.shoppingcart.R

class CartProductAdapter (private var cartProducts:ArrayList<Product>): RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartProductViewHolder {
        val layoutView: View = LayoutInflater.from(parent.context).
        inflate(R.layout.cart_product_card, parent, false)
        return CartProductViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val currentItem = cartProducts[position]
        holder.productImage.setImageResource(currentItem.productImage)
        holder.productTitle.text = currentItem.productName
        holder.productPrice.text = "₺" + currentItem.productPrice.toString()
        holder.decreaseButton.setOnClickListener {

        }
        holder.increaseButton.setOnClickListener {


        }
    }
    fun addToCart(product: Product, shoppingCart: Cart) { //add product to shopping cart
        if (shoppingCart.products.contains(product)) {
            shoppingCart.products.add(product)
///////////////////////////

        } else {
            shoppingCart.products.add(product)
            println("Product added to cart" + product.productID)
        }
    }
    override fun getItemCount(): Int {
        return cartProducts.size
    }

    class CartProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        var productImage: ImageView = view.findViewById(R.id.imageViewCartProduct)
        var productTitle: TextView = view.findViewById(R.id.textViewCartProductName)
        var productPrice: TextView = view.findViewById(R.id.textViewCartProductPrice)
        var decreaseButton: Button = view.findViewById(R.id.buttonDecrease)
        var increaseButton: Button = view.findViewById(R.id.buttonIncrease)
    }
}