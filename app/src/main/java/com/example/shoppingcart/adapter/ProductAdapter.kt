package com.example.shoppingcart.adapter


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.*
import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.view.fragment.ProductDetailFragment
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.model.Product
import com.example.shoppingcart.service.CartAPIService
import com.example.shoppingcart.util.downloadFromUrl
import com.example.shoppingcart.util.placeHolderProgressBar


class ProductAdapter(var products:ArrayList<Product>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    private val cartApiService = CartAPIService()

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
        holder.productImage.downloadFromUrl(
            currentProduct.productImage[0],
            placeHolderProgressBar(holder.productImage.context)) ///currentProduct.productImage
        holder.productTitle.text = currentProduct.productName
        holder.productPrice.text = "₺" + currentProduct.productPrice.toString()
        holder.productButton.setOnClickListener {
            addToCart(currentProduct, MockData.MockCart.cart)
        }
        holder.cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("productTitle", currentProduct.productName)
            bundle.putDouble("productPrice", currentProduct.productPrice)
            bundle.putString("productDescription", currentProduct.productDescription)
            bundle.putInt("productImage", R.drawable.shopping_cart_48px)  ///currentProduct.productImage
            val productDetailFragment = ProductDetailFragment()
            productDetailFragment.arguments = bundle
            val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            if (fragmentManager != null) {
                fragmentManager.beginTransaction().replace(R.id.frameLayout, productDetailFragment).addToBackStack(null).commit()
            }
        }
    }
    fun addToCart(product: Product, shoppingCart: Cart) { //add product to shopping cart
        /* var item:Item = Item(1, currentProduct, 1, 1, currentProduct.productPrice)
           cartApiService.addItemToCart(item)
           cartApiService.addProductToCart(product)
           */
        if (shoppingCart.items.isEmpty()){
            var cartItem: Item = Item(1, product, 1, 1, product.productPrice)
            shoppingCart.items.add(cartItem)
            Log.println(Log.INFO, TAG, "Product added to empty cart" + product.productName)
            return
        }
        for (item in shoppingCart.items) {
            if (item.product?.productID == product.productID) {
                item.quantity++
                Log.println(Log.INFO, TAG, "Product increase" + product.productName)
                return
            } else {
                var cartItem: Item = Item(2, product, 1, 1, product.productPrice)
                shoppingCart.items.add(cartItem)
                Log.println(Log.INFO, TAG, "Product added to cart" + product.productName)
                return
            }
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

    fun updateProductList(newProductList: List<Product>) {
        products.clear()
        products.addAll(newProductList)
        notifyDataSetChanged()
    }
}