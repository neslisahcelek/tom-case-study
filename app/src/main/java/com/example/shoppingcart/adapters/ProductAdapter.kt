package com.example.shoppingcart.adapters


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.Cart
import com.example.shoppingcart.Coupon
import com.example.shoppingcart.Product
import com.example.shoppingcart.R
import com.example.shoppingcart.fragments.ItemListFragment
import com.example.shoppingcart.fragments.ProductDetailFragment


class ProductAdapter(private var products:ArrayList<Product>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val layoutView:View = LayoutInflater.from(parent.context).
        inflate(com.example.shoppingcart.R.layout.product_card, parent, false)

        return ProductViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var currentProduct = products[position]
        holder.productImage.setImageResource(currentProduct.productImage)
        holder.productTitle.text = currentProduct.productName
        holder.productPrice.text = "₺" + currentProduct.productPrice.toString()
        holder.productButton.setOnClickListener {
            addToCart(products[position], Cart(1, arrayListOf(),0,0, Coupon(1,"",0,"desc")))
        }
        holder.cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("productTitle", currentProduct.productName)
            bundle.putInt("productPrice", currentProduct.productPrice)
            bundle.putString("productDescription", currentProduct.productDescription)
            bundle.putInt("productImage", currentProduct.productImage)
            val productDetailFragment = ProductDetailFragment()
            productDetailFragment.arguments = bundle
            val fragmentManager = ProductDetailFragment().activity?.supportFragmentManager
            if (fragmentManager != null) {
                fragmentManager.beginTransaction().replace(R.id.itemListFragment, productDetailFragment).commit()
            }
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
        return products.size
    }

    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        var productImage: ImageView = view.findViewById(com.example.shoppingcart.R.id.imageViewProduct)
        var productTitle: TextView = view.findViewById(com.example.shoppingcart.R.id.textViewProductTitle)
        var productPrice: TextView = view.findViewById(com.example.shoppingcart.R.id.textViewDetailProductPrice)
        var productButton: Button = view.findViewById(com.example.shoppingcart.R.id.buttonAddToCart)
        var cardView: View = view.findViewById(com.example.shoppingcart.R.id.cardViewProduct)
    }
}