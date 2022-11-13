package com.example.i_am_open

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController


class ProductDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        view.findViewById<TextView>(R.id.productVideoButton).setOnClickListener {
            view.findNavController().navigate(R.id.action_productDetailFragment_to_productVideoGuideFragment)
        }

//        view.findViewById<TextView>(R.id.productTutorialButton).setOnClickListener {
//            view.findNavController().navigate(R.id.action_productDetailFragment_to_productTutorialFragment)
//        }
        view.findViewById<TextView>(R.id.productLegalButton).setOnClickListener {
            view.findNavController().navigate(R.id.action_productDetailFragment_to_productLegalFragment)
        }
        return view

    }


}

