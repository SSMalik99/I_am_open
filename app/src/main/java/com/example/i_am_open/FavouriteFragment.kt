package com.example.i_am_open

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.i_am_open.adapters.FavouriteProductAdapter

class FavouriteFragment : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favourite, container, false)
        databaseHelper = DatabaseHelper(view.context)
        val recyclerView  = view.findViewById<RecyclerView>(R.id.favouriteFragmentRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val products = databaseHelper.likedProducts()

        val emptyMessage = view.findViewById<TextView>(R.id.noneFavouriteProduct)
        if (products.size < 1) {
            emptyMessage.visibility = View.VISIBLE
        }else{
            emptyMessage.visibility = View.GONE
            val productAdapter = FavouriteProductAdapter(view.context, products)
            recyclerView.adapter = productAdapter
        }


        return view
    }
}