package com.example.shoppingcart.adapter

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.*
import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.service.CartAPIService
import com.example.shoppingcart.view.fragment.CartFragment

class CartProductAdapter (var cartItems:ArrayList<Item>): RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder>(){
    var cartApiService: CartAPIService = CartAPIService()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartProductViewHolder {
        val layoutView: View = LayoutInflater.from(parent.context).
        inflate(R.layout.cart_product_card, parent, false)
        return CartProductViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val currentItem = cartItems[position]
        currentItem.product?.let { holder.productImage.setImageResource(R.drawable.shopping_cart_48px) } ///////
        holder.productTitle.text = currentItem.product?.productName ?: "Product Name"
        holder.productPrice.text = "₺" + currentItem.subtotal.toString()
        holder.productQuantity.text = currentItem.quantity.toString()
        holder.decreaseButton.setOnClickListener {
            Log.println(Log.INFO, ContentValues.TAG, "decrease button clicked")
            changeQuantity(currentItem, MockData.MockCart.cart, false)
            holder.productQuantity.text = currentItem.quantity.toString()
            holder.productPrice.text = "₺" + currentItem.subtotal.toString()
           // cartApiService.changeQuantity(currentItem)

        }
        holder.increaseButton.setOnClickListener {
            changeQuantity(currentItem, MockData.MockCart.cart, true)
            holder.productQuantity.text = currentItem.quantity.toString()
            holder.productPrice.text = "₺" + currentItem.subtotal.toString()
        }
    }
    fun changeQuantity(item: Item, shoppingCart: Cart, isIncrease: Boolean) { //add product to shopping cart
        if (shoppingCart.items.contains(item)) {
            if (isIncrease) {
                item.quantity = item.quantity + 1 //increase quantity
            } else {
                item.quantity = item.quantity - 1 //decrease quantity
                Log.println(Log.INFO, ContentValues.TAG, "quantity decreased" + item.quantity)
                if (item.quantity == 0) { //if quantity is 0, remove item from cart
                    shoppingCart.items.remove(item)
                    return
                }
            }
            item.subtotal = item.quantity * item.product!!.productPrice
            Log.println(Log.INFO, ContentValues.TAG, "subtotal changed" + item.subtotal)
            return
        }
    }
    override fun getItemCount(): Int {
        return cartItems.size
    }

    class CartProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        var productImage: ImageView = view.findViewById(R.id.imageViewCartProduct)
        var productTitle: TextView = view.findViewById(R.id.textViewCartProductName)
        var productPrice: TextView = view.findViewById(R.id.textViewCartProductPrice)
        var decreaseButton: Button = view.findViewById(R.id.buttonDecrease)
        var increaseButton: Button = view.findViewById(R.id.buttonIncrease)
        var productQuantity: TextView = view.findViewById(R.id.textViewQuantity)
    }
}