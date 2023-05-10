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
import com.example.shoppingcart.R
import com.example.shoppingcart.model.Cart
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.service.CartAPIService
import com.example.shoppingcart.util.downloadFromUrl
import com.example.shoppingcart.util.placeHolderProgressBar
import com.example.shoppingcart.view.fragment.ProductDetailFragment


class ProductAdapter(var products:ArrayList<Item>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
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
            currentProduct.ItemImage,
            placeHolderProgressBar(holder.productImage.context)) ///currentProduct.productImage
        holder.productTitle.text = currentProduct.ItemName
        holder.productPrice.text = "₺" + currentProduct.ItemPrice.toString()
        holder.productButton.setOnClickListener {
            addToCart(currentProduct, MockData.MockCart.cart)
        }
        holder.cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("productTitle", currentProduct.ItemName)
            bundle.putDouble("productPrice", currentProduct.ItemPrice)
            bundle.putString("productDescription", currentProduct.ItemDescription)
            bundle.putString("productImage", currentProduct.ItemImage)
            bundle.putString("productID", currentProduct.ItemID)
            bundle.putSerializable("product", currentProduct)

            val productDetailFragment = ProductDetailFragment()
            productDetailFragment.arguments = bundle
            val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            if (fragmentManager != null) {
                fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                    .replace(R.id.frameLayout, productDetailFragment).addToBackStack(null).commit()
            }
        }
    }
    private fun addToCart(product: Item, shoppingCart: Cart) { //add product to shopping cart
        /* var item:Item = Item(1, currentProduct, 1, 1, currentProduct.productPrice)
           cartApiService.addItemToCart(item)
           cartApiService.addProductToCart(product)
           */
            var itemExist:Boolean = false
            for (item in shoppingCart.items!!) {
                if (item.ItemID == product.ItemID) {
                    Log.println(Log.INFO, TAG, "item  exist")
                    item.quantity = item.quantity + 1
                    shoppingCart.totalPrice = shoppingCart.totalPrice?.plus(product.ItemPrice)
                    itemExist = true
                    Log.println(Log.INFO, TAG, "Product increase")
                    break
                }
            }
            if(itemExist == false){
                Log.println(Log.INFO, TAG, "item not exist")
                shoppingCart.items!!.add(product)
                shoppingCart.totalPrice = shoppingCart.totalPrice?.plus(product.ItemPrice)
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

    fun updateProductList(newProductList: List<Item>) {
        products.clear()
        products.addAll(newProductList)
        notifyDataSetChanged()
    }
}