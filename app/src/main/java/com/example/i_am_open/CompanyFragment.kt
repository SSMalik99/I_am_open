package com.example.i_am_open

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.findNavController

class CompanyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_company, container, false)

        val company1= view.findViewById<LinearLayout>(R.id.company1)
//        company1.setOnClickListener{
//            company1.findNavController().navigate(R.id.action_companyFragment_to_companyDetailFragment)
//        }
        return view
    }

}