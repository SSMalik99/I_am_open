package com.example.i_am_open

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.i_am_open.adapters.TutorialAdapter
import com.google.android.material.button.MaterialButton
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

        val likeButton = view.findViewById<ImageButton>(R.id.btn_like)

        if (databaseHelper.isProductLiked(productId)) likeButton.setImageResource(R.drawable.heart) else likeButton.setImageResource(R.drawable.favorite_border)


        likeButton.setOnClickListener {
            likeButton.isClickable = false
            databaseHelper.syncProductLike(productId)

            if (databaseHelper.isProductLiked(productId)) likeButton.setImageResource(R.drawable.heart) else likeButton.setImageResource(R.drawable.favorite_border)

            likeButton.isClickable = true

        }

        view.findViewById<TextView>(R.id.productVideoButton).setOnClickListener {
            // code to navigate to product video guide fragment and send product data along with it
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToProductVideoGuideFragment2(productId)
            view.findNavController().navigate(action)
        }

        view.findViewById<TextView>(R.id.productLegalButton).setOnClickListener {
            // code to navigate to product legal fragment and send product data along with it
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToProductLegalFragment2(productId)
            view.findNavController().navigate(action)
        }


        val recyclerView  = view.findViewById<RecyclerView>(R.id.tutorialListView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        val tutorials = databaseHelper.productTutorial(productId, TutorialType.READABLE)

        val tutorialAdapter = TutorialAdapter(view.context, tutorials)
        recyclerView.adapter = tutorialAdapter


        return view

    }


}

