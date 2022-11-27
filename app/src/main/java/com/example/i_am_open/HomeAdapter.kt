package com.example.i_am_open

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class HomeAdapter(private val myContext: Context,
                  private val arrayList: ArrayList<ProductModel>):
    ArrayAdapter<ProductModel>(myContext, R.layout.home_list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(myContext)
        val view: View =  inflater.inflate(R.layout.home_list_item, null)
        val imageView: ImageView = view.findViewById(R.id.productImage)
        val name: TextView = view.findViewById(R.id.productName)
        val request: TextView = view.findViewById(R.id.request)
        val count: TextView =  view.findViewById(R.id.count)

        Glide.with(myContext).load(arrayList[position].image).into(imageView);
        name.text = arrayList[position].name
        request.text = arrayList[position].upVote.toString()
        count.text = arrayList[position].downVote.toString()

        return view
    }

}