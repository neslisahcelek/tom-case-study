package com.example.shoppingcart.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.R
import com.example.shoppingcart.databinding.FragmentProductDetailBinding
import com.example.shoppingcart.viewModel.ProductDetailViewModel
import com.example.shoppingcart.viewModel.ProductListViewModel


class ProductDetailFragment: Fragment() {
    private lateinit var viewModel: ProductDetailViewModel
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(ProductDetailViewModel::class.java)
        viewModel.getDataFromRoom()
        observeLiveData()
        /*arguments?.let {
            val safeArgs = ProductDetailFragmentArgs.fromBundle(it)
            val productTitle = safeArgs.productTitle
            val productImage = safeArgs.productImage
            val productPrice = safeArgs.productPrice
            val productDescription = safeArgs.productDescription
        }
         */
    }
    private fun observeLiveData() {
        viewModel.productLiveData.observe(viewLifecycleOwner, { product ->
            product?.let {
                //binding.imageViewDetail.setImageResource(product.productImage)
                binding.textViewDetailProductName.text = product.productName
                binding.textViewDetailProductPrice.text = product.productPrice.toString()
                binding.textViewDetailProductDescription.text = product.productDescription
            }
        })
    }

    fun addToCart(view:View) { //add product to shopping cart

    }



}