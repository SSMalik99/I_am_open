package com.example.i_am_open

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ProductVideoGuideFragment : Fragment() {

    private var productId : Int = 0
    lateinit var databaseHelper : DatabaseHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        productId = arguments?.getInt("id")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_product_video_guide, container, false)
        databaseHelper = DatabaseHelper(view.context)
        val tutorials = databaseHelper.productTutorial(1,TutorialType.VIDEO)

        return view
    }


}