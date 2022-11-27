package com.example.i_am_open

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class CompanyDetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_company_details, container, false)

        // Receive Product Id from home fragment
        val id = CompanyDetailsFragmentArgs.fromBundle(requireArguments()).id
        Toast.makeText(activity, id.toString(),
            Toast.LENGTH_LONG).show();

        return view
    }

}