package com.example.i_am_open

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class CompanyDetailsFragment : Fragment() {

    private var companyId : Int = 0
    lateinit var databaseHelper : DatabaseHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        companyId = arguments?.getInt("id")!!
    }


    var products = ArrayList<ProductModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_company_details, container, false)
        databaseHelper = DatabaseHelper(view.context!!)
        val company = databaseHelper.singleCompany(companyId)

        var imageView = view.findViewById<ImageView>(R.id.companyImage)
        Glide.with(this).load(company.image).into(imageView);

        // Receive Product Id from home fragment
        val id = CompanyDetailsFragmentArgs.fromBundle(requireArguments()).id
        Toast.makeText(activity, id.toString(),
            Toast.LENGTH_LONG).show();

//        myContext = container?.context
//        // Inflate the layout for this fragment
//
//        databaseHelper = DatabaseHelper(myContext!!)
//
        products = databaseHelper.allProducts()


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = view.findViewById<ListView>(R.id.productListView)
        listView.adapter = activity?.let { HomeAdapter(it, products) }
        listView.setOnItemClickListener(){adapterView, view, position, id ->
            val id = products[position]?.id
            val action = CompanyDetailsFragmentDirections.actionCompanyDetailsFragmentToProductDetailFragment(id)
            listView.findNavController().navigate(action)

        }
    }

}