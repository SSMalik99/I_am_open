package com.example.i_am_open.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.i_am_open.FavouriteFragmentDirections
import com.example.i_am_open.ProductModel
import com.example.i_am_open.R

class FavouriteProductAdapter(val context: Context, val products : ArrayList<ProductModel>)
    :RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.favourite_product_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = products.get(position)
        Glide.with(this.context).load(product.image).into(holder.productImage);
        holder.productName.text = product.name
        holder.productId = product.id
        if (position%2 == 0){
            holder.productName.setTextColor(ContextCompat.getColor(this.context, R.color.white))
            holder.listContainer.setBackgroundColor(ContextCompat.getColor(
                this.context,
                com.google.android.material.R.color.material_blue_grey_800
            ))
        }else if (position % 3 == 0 ){
            holder.productName.setTextColor(ContextCompat.getColor(this.context, R.color.black))
            holder.listContainer.setBackgroundColor(ContextCompat.getColor(
                this.context,
                R.color.white
            ))
        }else{
            holder.productName.setTextColor(ContextCompat.getColor(this.context, androidx.appcompat.R.color.material_blue_grey_800))
            holder.listContainer.setBackgroundColor(ContextCompat.getColor(
                this.context,
                R.color.blue_light
            ))
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }
}

class MyViewHolder(val view: View) :RecyclerView.ViewHolder(view){

    val listContainer = view.findViewById<LinearLayout>(R.id.linearLayoutContainerFavouriteList)!!
    val productImage = view.findViewById<ImageView>(R.id.favouriteProductImage)
    val productName = view.findViewById<TextView>(R.id.productName)
    var productId :Int = 0
    init {
        // Define click listener for the ViewHolder's View.
        productName.setOnClickListener{
            view.findNavController().navigate(FavouriteFragmentDirections.actionFavouriteFragmentToProductDetailFragment(productId))

        }
    }

}