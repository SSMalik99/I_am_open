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

    val companyImages = arrayOf<Int>(
        R.drawable.company1,
        R.drawable.company2,
        R.drawable.company,
        R.drawable.company1,
        R.drawable.company2,
        R.drawable.company
    )

    lateinit var databaseHelper : DatabaseHelper
    val companyNames = arrayOf<String>(
        "Samsung Company",
        "Adobe Illustrator",
        "Canon",
        "Drone Company",
        "Meta Company",
        "Hoverboard Company"
    )

    val companyDescriptions = arrayOf<String>(
        "This is a Samsung Company",
        "This is a Adobe Illustrator",
        "This is a Canon",
        "This is a Drone Company",
        "This is a Meta Company",
        "This is a Hoverboard Company"
    )

    var companyIds =  ArrayList<Int>()
    var companyList = ArrayList<Company>()
    var myContext: Context? = null

    fun populateCompanies() {
        companyList = arrayListOf<Company>()
        companyIds =  arrayListOf<Int>()
        for (i in companyNames.indices) {
            companyIds.add(i+1)
            val company = Company(
                i+1,
                companyImages[i], companyNames[i],
                companyDescriptions[i]
            )
            companyList.add(company)
        }
    }

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
        listView.setOnItemClickListener(){adapterView, view, position, id ->
            val id = companies[position].id
            val action = CompanyFragmentDirections.actionCompanyFragmentToCompanyDetailsFragment2(id)
            listView.findNavController().navigate(action)
        }
    }

}