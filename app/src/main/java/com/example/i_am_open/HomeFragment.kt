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
            view.findNavController().navigate(R.id.action_homeFragment_to_companyFragment)
        }
        val item1 = view.findViewById<ConstraintLayout>(R.id.item_1)
        item1.setOnClickListener {
            item1.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
        }

        val item2 = view.findViewById<ConstraintLayout>(R.id.item_2)
        item2.setOnClickListener {
            item2.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
        }

        val item3 = view.findViewById<ConstraintLayout>(R.id.item_3)
        item3.setOnClickListener {
            item3.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
        }

        val item4 = view.findViewById<ConstraintLayout>(R.id.item_4)
        item4.setOnClickListener {
            item4.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
        }

        val item5 = view.findViewById<ConstraintLayout>(R.id.item_5)
        item5.setOnClickListener {
            item5.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
        }

        val item6 = view.findViewById<ConstraintLayout>(R.id.item_6)
        item6.setOnClickListener {
            item6.findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
        }


        return view
    }
}