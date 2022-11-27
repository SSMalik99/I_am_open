package com.example.i_am_open

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mContext = context
//    }

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
        listView.setOnItemClickListener(){adapterView, view, position, id ->
            val id = products[position]?.id
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(id)
            listView.findNavController().navigate(action)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(myContext, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = CompanyImageListAdapter(companies)
    }

}



