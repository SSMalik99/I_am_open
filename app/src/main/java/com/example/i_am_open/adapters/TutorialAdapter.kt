package com.example.i_am_open.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.i_am_open.*

class TutorialAdapter(val context: Context, val tutorials : ArrayList<TutorialModel>)
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

    override fun onBindViewHolder(holder: TutorialViewHolder, position: Int) {
        val tutorial = tutorials[position]
        holder.tutorialTitle.text = tutorial.title
        holder.tutorialId = tutorial.id
        val description = tutorial.description

        (if (description.length > 300) {
            description.substring(0, 300) + "..."
        } else {
            description
        }).also { holder.tutorialDescription.text = it }

        if (position%2 == 0){

            holder.tutorialCard.setBackgroundColor(
                ContextCompat.getColor(
                this.context,
                R.color.green_light
            ))
        }else if (position % 3 == 0 ){

            holder.tutorialCard.setBackgroundColor(
                ContextCompat.getColor(
                this.context,
                R.color.white
            ))
        }else{

            holder.tutorialCard.setBackgroundColor(
                ContextCompat.getColor(
                this.context,
                R.color.blue_light
            ))
        }

    }

    override fun getItemCount(): Int {
        return tutorials.size
    }
}

class TutorialViewHolder(val view: View) :RecyclerView.ViewHolder(view) {
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