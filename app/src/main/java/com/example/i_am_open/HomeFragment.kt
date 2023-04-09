package com.example.i_am_open

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    lateinit var databaseHelper : DatabaseHelper
    var myContext: Context? = null
    var products = ArrayList<ProductModel>()
    var companies = ArrayList<CompanyModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myContext = container?.context

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val viewAllBtn = view.findViewById<Button>(R.id.view_all_btn)

        // Code to initialize database helper class
        databaseHelper = DatabaseHelper(myContext!!)
        companies = databaseHelper.allCompanies()
        products = databaseHelper.allProducts()

        viewAllBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_companyFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = view.findViewById<ListView>(R.id.productListView)
        listView.adapter = activity?.let { HomeAdapter(it, products) }

        // Code to add click listener to each item within the list view
        listView.setOnItemClickListener(){adapterView, view, position, id ->
            val id = products[position]?.id

            // code to navigate to product detail fragment and send product data along with it
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(id)
            listView.findNavController().navigate(action)
        }

        // Use of recycler view to list companies in a horizontal scroll view
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(myContext, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = CompanyImageListAdapter(companies)
    }

}



