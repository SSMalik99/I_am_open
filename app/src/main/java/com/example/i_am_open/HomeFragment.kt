package com.example.i_am_open

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val viewAllBtn = view.findViewById<Button>(R.id.view_all_btn)
        viewAllBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment2_to_companyFragment2)
        }
//        val product1 = view.findViewById<ConstraintLayout>(R.id.product1)
//        product1.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
//
//        val product2 = view.findViewById<ConstraintLayout>(R.id.product2)
//        product2.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
//
//        val product3 = view.findViewById<ConstraintLayout>(R.id.product3)
//        product3.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
//
//        val product4 = view.findViewById<ConstraintLayout>(R.id.product4)
//        product4.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
//
//        val product5 = view.findViewById<ConstraintLayout>(R.id.product5)
//        product5.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
//
//        val product6 = view.findViewById<ConstraintLayout>(R.id.product6)
//        product6.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)

        return view
    }
}