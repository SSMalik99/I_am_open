package com.example.i_am_open

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import androidx.navigation.findNavController

class CompanyFragment : Fragment() {

    lateinit var databaseHelper : DatabaseHelper
    var myContext: Context? = null
    var companies = ArrayList<CompanyModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_company, container, false)
        myContext = view.context

        databaseHelper = DatabaseHelper(myContext!!)

        companies = databaseHelper.allCompanies()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = view.findViewById<ListView>(R.id.companyListView)
        listView.adapter = activity?.let { CompanyAdapter(it, companies) }

        // Code to add click listener within the company listview
        listView.setOnItemClickListener(){adapterView, view, position, id ->
            val id = companies[position].id

            // code to navigate to company detail fragment and send company data along with it
            val action = CompanyFragmentDirections.actionCompanyFragmentToCompanyDetailsFragment2(id)
            listView.findNavController().navigate(action)
        }
    }

}