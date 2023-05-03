package com.example.shoppingcart.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.adapters.ProductAdapter
import com.example.shoppingcart.databinding.FragmentProductDetailBinding
import com.example.shoppingcart.mock.MockData


class ProductDetailFragment: Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    fun ProductDetailsFragment() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val view = binding.root
       // var view = inflater.inflate(com.example.shoppingcart.R.layout.fragment_product_detail, container, false)
        val productTitle = arguments?.getString("productTitle")
        val productImage = arguments?.getInt("productImage")
        val productPrice = arguments?.getString("productPrice")
        val productDescription = arguments?.getString("productDescription")
        val productDetailImage = arguments?.getInt("productDetailImage")

        var productImageView = binding.imageViewDetail
        if (productImage != null) {
            productImageView.setImageResource(productImage)
        }
        var productTitleView = binding.textViewDetailProductName
        if (productTitle != null) {
            productTitleView.text = productTitle
        }
        var productPriceView = binding.textViewDetailProductPrice
        if (productPrice != null) {
            productPriceView.text = productPrice
        }
        var productDescriptionView = binding.textViewDetailProductDescription
        if (productDescription != null) {
            productDescriptionView.text = productDescription
        }

        return view
    }



}