package com.example.i_am_open

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val viewAllBtn = view.findViewById<Button>(R.id.view_all_btn);

        viewAllBtn.setOnClickListener {

//            val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
//            bottomNavigationView.selectedItemId = R.id.companyFragment
            view.findNavController().navigate(R.id.action_homeFragment_to_companyFragment)
//            view.findNavController().navigate(R.id.action_homeFragment_to_companyFragment)
        }
        return view
    }


}