package com.example.shoppingcart.view.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.example.shoppingcart.databinding.FragmentProductDetailBinding
import com.example.shoppingcart.model.Item
import com.example.shoppingcart.model.MockData
import com.example.shoppingcart.model.Product
import com.example.shoppingcart.service.CartAPIService
import com.example.shoppingcart.util.downloadFromUrl
import com.example.shoppingcart.util.placeHolderProgressBar
import com.example.shoppingcart.viewModel.ProductDetailViewModel
import com.example.shoppingcart.viewModel.ProductListViewModel


class ProductDetailFragment: Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var viewModel: ProductDetailViewModel
    private var productUuid = 0
    private val cartApiService = CartAPIService()

    companion object {
        fun newInstance(title: String, imageResource: Int, price: String,product:Product): ProductDetailFragment {
            val args = Bundle()
            args.putSerializable("product", product)
            args.putInt("productID", product.productID)
            args.putString("productTitle", title)
            args.putInt("productImage", imageResource)
            args.putString("productPrice", price)
            val fragment = ProductDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val productTitle = arguments?.getString("productTitle")
        val productImage = arguments?.getString("productImage")
        val productPrice = arguments?.getDouble("productPrice")
        val productDescription = arguments?.getString("productDescription")
        val productId = arguments?.getInt("productID")
        val currentProduct = arguments?.getSerializable("product") as? Product

        if (productImage != null) {
            context?.let { placeHolderProgressBar(it) }?.let {
                binding.imageViewDetail.downloadFromUrl(
                    productImage,
                    it)
            }
        }
        if (productTitle != null) {
            binding.textViewDetailProductName.text = productTitle
        }
        if (productPrice != null) {
            binding.textViewDetailProductPrice.text = "₺"+ productPrice.toString()
        }
        if (productDescription != null) {
            binding.textViewDetailProductDescription.text = productDescription
        }

        binding.buttonAddtoCart.setOnClickListener {
            if (currentProduct != null) {
                addToCart(currentProduct)
                //cartApiService.addProductToCart(productId)
            }
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(ProductDetailViewModel::class.java)

        /*arguments?.let {
            val safeArgs = ProductDetailFragmentArgs.fromBundle(it)
            val productTitle = safeArgs.productTitle
            val productImage = safeArgs.productImage
            val productPrice = safeArgs.productPrice
            val productDescription = safeArgs.productDescription
            val uuid = safeArgs.productUuid
        }
        //viewModel.getDataFromRoom(uuid)
        //observeLiveData()
         */
    }
    private fun observeLiveData() {
        viewModel.productLiveData.observe(viewLifecycleOwner) { product ->
            product?.let {
                context?.let { it1 -> placeHolderProgressBar(it1) }?.let { it2 ->
                    binding.imageViewDetail.downloadFromUrl(product.productImage, it2)
                }
                binding.textViewDetailProductName.text = product.productName
                binding.textViewDetailProductPrice.text = product.productPrice.toString()
                binding.textViewDetailProductDescription.text = product.productDescription
            }
        }
    }

    fun addToCart(product: Product) { //add product to shopping cart
        var shoppingCart = MockData.MockCart.cart
        if (shoppingCart.items.isEmpty()){
            var cartItem: Item = Item(1, product, 1, 1, product.productPrice)
            shoppingCart.items.add(cartItem)
            Log.println(Log.INFO, ContentValues.TAG, "Product added to empty cart" + product.productName)
            return
        }
        for (item in shoppingCart.items) {
            if (item.product?.productID == product.productID) {
                item.quantity++
                Log.println(Log.INFO, ContentValues.TAG, "Product increase" + product.productName)
                return
            } else {
                var cartItem: Item = Item(2, product, 1, 1, product.productPrice)
                shoppingCart.items.add(cartItem)
                Log.println(Log.INFO, ContentValues.TAG, "Product added to cart" + product.productName)
                return
            }
        }
    }



}