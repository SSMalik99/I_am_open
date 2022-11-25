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
import androidx.navigation.findNavController

class HomeFragment : Fragment() {

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mContext = context
//    }

    val productImages = arrayOf<Int>(
        R.drawable.product1,
        R.drawable.adobe,
        R.drawable.canon_printer,
        R.drawable.drone,
        R.drawable.vr_set,
        R.drawable.hovernoard
    )

    lateinit var databaseHelper : DatabaseHelper
    val productNames = arrayOf<String>(
        "Samsung Memory Expander",
        "Adobe Illustrator",
        "Canon Printer",
        "Drone With Camera",
        "Meta VR Set",
        "Hoverboard"
    )

    val productRequestNumbers = arrayOf<Int>(
        100, 50, 25, 40, 50, 30
    )

    val productVotes = arrayOf<Int>(
        100, 50, 25, 40, 50, 30
    )

    var productIds =  ArrayList<Int>()



    var productList = ArrayList<RecentlyViewedProduct>()
    var myContext: Context? = null

    fun populateProducts() {
        productList = arrayListOf<RecentlyViewedProduct>()
        productIds =  arrayListOf<Int>()
        for (i in productNames.indices) {
            productIds.add(i+1)
            val product = RecentlyViewedProduct(
                i+1,
                productImages[i], productNames[i],
                productRequestNumbers[i], productVotes[i]
            )
            productList.add(product)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myContext = container?.context
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val viewAllBtn = view.findViewById<Button>(R.id.view_all_btn)


        databaseHelper = DatabaseHelper(myContext!!)

        Log.i("database Info-----",databaseHelper.test())

        viewAllBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_companyFragment)
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateProducts()
        val listView = view.findViewById<ListView>(R.id.productListView)
        listView.adapter = activity?.let { HomeAdapter(it, productList) }
        listView.setOnItemClickListener(){adapterView, view, position, id ->
            Toast.makeText(getActivity(), productIds[position].toString()+" "+productNames[position],
                Toast.LENGTH_LONG).show();
            listView.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
        }
    }

}



