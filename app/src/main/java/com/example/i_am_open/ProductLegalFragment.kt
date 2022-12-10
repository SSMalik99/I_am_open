package com.example.i_am_open

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import kotlin.system.exitProcess


class ProductLegalFragment : Fragment() {

    lateinit var databaseHelper: DatabaseHelper
    var productId = 0
    override fun onAttach(context: Context) {
        super.onAttach(context)
        productId = arguments?.getInt("productId")!!
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val actionBar = requireActivity().actionBar

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_legal, container, false)
        val databaseHelper = DatabaseHelper(view.context)

        val product = databaseHelper.singleProduct(productId)
        val precaution = databaseHelper.getProductPrecaution(productId)

        val imageView = view.findViewById<ImageView>(R.id.precautionProductImage)
        val precautionTitle  = view.findViewById<TextView>(R.id.precautionTitle)
        val precautionDesctiption = view.findViewById<TextView>(R.id.precautionDescription)

        Glide.with(this).load(product.image).into(imageView);

        precautionTitle.text = precaution.title
        precautionDesctiption.text = precaution.description

        return view
    }






}