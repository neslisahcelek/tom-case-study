package com.example.shoppingcart.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingcart.*
import com.example.shoppingcart.adapter.ProductAdapter
import com.example.shoppingcart.view.MainActivity
import com.example.shoppingcart.viewModel.ProductListViewModel

class ProductListFragment : Fragment() {
    private lateinit var viewModel: ProductListViewModel
    private val productAdapter = ProductAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_item_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(ProductListViewModel::class.java)
        viewModel.refreshData()

        val recyclerView:RecyclerView = view.findViewById(R.id.recyclerViewItemList)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this.context,2,
            GridLayoutManager.VERTICAL, false)

        /*var main: MainActivity = activity as MainActivity
        val adapter = viewModel.products.let { ProductAdapter(it) } //all products from database
         */
        recyclerView.adapter = productAdapter
        //adapter?.notifyDataSetChanged()
    }
    fun observeLiveData(){
        val recyclerView:RecyclerView = view?.findViewById(R.id.recyclerViewItemList) ?: return
        val errorText:TextView = view?.findViewById(R.id.itemListError) ?: return
        val loadBar:ProgressBar = view?.findViewById(R.id.itemListLoading) ?: return

        viewModel.products.observe(viewLifecycleOwner, Observer {
            products -> products?.let {
            recyclerView.visibility = View.VISIBLE
            productAdapter.updateProductList(products)
            }
        })
        viewModel.productError.observe(viewLifecycleOwner, Observer {
                error -> error?.let {
                    if(it){
                        errorText.visibility = View.VISIBLE
                    }else{
                        errorText.visibility = View.GONE
                    }
        }
        })
        viewModel.productLoading.observe(viewLifecycleOwner, Observer {
                loading -> loading?.let {
                if(it){
                    loadBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    errorText.visibility = View.GONE
                }else{
                    loadBar.visibility = View.GONE
                }
             }
        })
    }
}