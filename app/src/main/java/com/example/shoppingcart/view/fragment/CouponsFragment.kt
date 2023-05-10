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
import com.example.shoppingcart.R
import com.example.shoppingcart.adapter.CouponAdapter
import com.example.shoppingcart.viewModel.CouponsViewModel

class CouponsFragment : Fragment() {
    private lateinit var viewModel: CouponsViewModel
    private val couponAdapter = CouponAdapter(arrayListOf())
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar?.setTitle("Coupons")
        var view = inflater.inflate(R.layout.fragment_coupons, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(CouponsViewModel::class.java)
        viewModel.refreshData()

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewCoupons)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this.context,1,
            GridLayoutManager.VERTICAL, false)

        //val adapter = CouponAdapter(MockData.MockCoupon.couponList) //coupons from database

        recyclerView.adapter = couponAdapter

        val errorText: TextView = view?.findViewById(R.id.couponsError) ?: return
        val loadBar: ProgressBar = view?.findViewById(R.id.couponsLoading) ?: return
        val refresh: SwipeRefreshLayout = view.findViewById(R.id.swipeRefreshCoupons)

        refresh.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            errorText.visibility = View.GONE
            loadBar.visibility = View.VISIBLE
            refresh.isRefreshing = false
            viewModel.refreshData()
            refresh.isRefreshing = false
        }
        observeLiveData()
        //adapter?.notifyDataSetChanged()
    }
    private fun observeLiveData(){
        val recyclerView:RecyclerView = view?.findViewById(R.id.recyclerViewCoupons) ?: return
        val errorText: TextView = view?.findViewById(R.id.couponsError) ?: return
        val loadBar: ProgressBar = view?.findViewById(R.id.couponsLoading) ?: return

        viewModel.coupons.observe(viewLifecycleOwner, Observer {
                coupons -> coupons?.let {
            errorText.visibility = View.GONE
            loadBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            couponAdapter.updateCouponList(coupons)
                }
        })
        viewModel.couponsError.observe(viewLifecycleOwner, Observer {
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
        viewModel.couponsLoading.observe(viewLifecycleOwner, Observer {
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