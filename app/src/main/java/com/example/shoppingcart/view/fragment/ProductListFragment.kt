package com.example.shoppingcart.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
        (activity as AppCompatActivity?)!!.supportActionBar?.setTitle("Products")
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

        val errorText:TextView = view?.findViewById(R.id.itemListError) ?: return
        val loadBar:ProgressBar = view?.findViewById(R.id.itemListLoading) ?: return
        val refresh: SwipeRefreshLayout = view.findViewById(R.id.swipeRefreshItemList)

        refresh.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            errorText.visibility = View.GONE
            loadBar.visibility = View.VISIBLE
            refresh.isRefreshing = false
            viewModel.refreshFromAPI()
            refresh.isRefreshing = false
        }
        observeLiveData()
        //adapter?.notifyDataSetChanged()
    }
    private fun observeLiveData(){
        val recyclerView:RecyclerView = view?.findViewById(R.id.recyclerViewItemList) ?: return
        val errorText:TextView = view?.findViewById(R.id.itemListError) ?: return
        val loadBar:ProgressBar = view?.findViewById(R.id.itemListLoading) ?: return

        viewModel.products.observe(viewLifecycleOwner, Observer {
            products -> products?.let {
            errorText.visibility = View.GONE
            loadBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            productAdapter.updateProductList(products)
            }
        })
        viewModel.productError.observe(viewLifecycleOwner, Observer {
                error -> error?.let {
                    if(it){
                        recyclerView.visibility = View.GONE
                        loadBar.visibility = View.GONE
                        errorText.visibility = View.VISIBLE
                    }else{
                        errorText.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        loadBar.visibility = View.GONE
                    }
        }
        })
        viewModel.productLoading.observe(viewLifecycleOwner, Observer {
                loading -> loading?.let {
                if(it){
                    recyclerView.visibility = View.GONE
                    errorText.visibility = View.GONE
                    loadBar.visibility = View.VISIBLE
                }else{
                    loadBar.visibility = View.GONE
                    errorText.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
             }
        })
    }
}