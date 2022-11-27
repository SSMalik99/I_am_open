package com.example.i_am_open.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.i_am_open.ProductDetailFragmentDirections
import com.example.i_am_open.R
import com.example.i_am_open.TutorialModel

class VideoTutorialAdapter(val context: Context, val videos : ArrayList<TutorialModel>)
    : RecyclerView.Adapter<TutorialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialViewHolder {
        return TutorialViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.product_tutorial_item,
                parent,
                false
            )
        )
    }

    //Data
    //XML file
    //Adapter
    //HolderClass

    override fun onBindViewHolder(holder: TutorialViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

class VideoTutorialHolder(val view: View) :RecyclerView.ViewHolder(view) {
    var tutorialId = 0
    val tutorialCard = view.findViewById<CardView>(R.id.tutorialCardView)!!
    val tutorialTitle = view.findViewById<TextView>(R.id.tutrialTitle)!!
    val tutorialDescription = view.findViewById<TextView>(R.id.tutorialDiscription)!!

    init {
        tutorialTitle.setOnClickListener{
            view.findNavController().navigate(ProductDetailFragmentDirections.actionProductDetailFragmentToProductTutorialFragment2(tutorialId))
        }
    }
}