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

    private lateinit var binding: FragmentProductDetailBinding

    companion object {
        fun newInstance(title: String, imageResource: Int, price: String): ProductDetailFragment {
            val args = Bundle()
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
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val productTitle = arguments?.getString("productTitle")
        val productImage = arguments?.getInt("productImage")
        val productPrice = arguments?.getString("productPrice")
        val productDescription = arguments?.getString("productDescription")

        if (productImage != null) {
            binding.imageViewDetail.setImageResource(productImage)
        }
        if (productTitle != null) {
            binding.textViewDetailProductName.text = productTitle
        }
        if (productPrice != null) {
            binding.textViewDetailProductPrice.text = productPrice
        }
        if (productDescription != null) {
            binding.textViewDetailProductDescription.text = productDescription
        }

        binding.buttonAddtoCart.setOnClickListener {
            //
        }

        return binding.root
    }



}