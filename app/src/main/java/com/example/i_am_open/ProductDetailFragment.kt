package com.example.i_am_open

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import kotlin.math.log
import kotlin.properties.Delegates


class ProductDetailFragment : Fragment() {

    private var productId : Int = 0
    lateinit var databaseHelper : DatabaseHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        productId = arguments?.getInt("id")!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)
        databaseHelper = DatabaseHelper(view.context!!)
        val product = databaseHelper.singleProduct(productId)


        var imageView = view.findViewById<ImageView>(R.id.product_detail_image)
        Glide.with(this).load(product.image).into(imageView);

        view.findViewById<TextView>(R.id.productName).text = product.name
        view.findViewById<TextView>(R.id.productDescription).text = product.description

        view.findViewById<TextView>(R.id.productVideoButton).setOnClickListener {
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToProductVideoGuideFragment()
            view.findNavController().navigate(action)
        }

        view.findViewById<TextView>(R.id.productLegalButton).setOnClickListener {
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToProductLegalFragment()
            view.findNavController().navigate(action)
//            view.findNavController().navigate(R.id.action_productDetailFragment_to_productLegalFragment)
        }

        val title1 = view.findViewById<TextView>(R.id.tutorialTitle1)
        val title2 = view.findViewById<TextView>(R.id.tutorialTitle2)

        title1.setOnClickListener {
            view.findNavController().navigate(R.id.action_productDetailFragment_to_productTutorialFragment)
        }
        title1.setOnClickListener {
            view.findNavController().navigate(R.id.action_productDetailFragment_to_productTutorialFragment)
        }

        return view

    }


}

