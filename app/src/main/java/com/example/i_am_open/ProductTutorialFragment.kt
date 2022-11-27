package com.example.i_am_open

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ProductTutorialFragment : Fragment() {

    var tutorialId = 0
    lateinit var databaseHelper: DatabaseHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        tutorialId = arguments?.getInt("tutorialId")!!

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_tutorial, container, false)
        databaseHelper = DatabaseHelper(view.context)

        val tutorial = databaseHelper.singleTutorial(tutorialId)
        val product = databaseHelper.singleProduct(tutorial.productId)

        val titleView = view.findViewById<TextView>(R.id.productTutorialTitle)
        val descriptionView = view.findViewById<TextView>(R.id.productTutorialDescription)
        val imageView = view.findViewById<ImageView>(R.id.productTutorialImage)
        Glide.with(this).load(product.image).into(imageView);

        titleView.text = tutorial.title
        descriptionView.text = tutorial.description



        return view
    }


}