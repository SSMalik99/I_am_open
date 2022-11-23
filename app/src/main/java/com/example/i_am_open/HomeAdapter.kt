package com.example.i_am_open

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class HomeAdapter(private val myContext: Context,
                  private val arrayList: ArrayList<RecentlyViewedProduct>):
    ArrayAdapter<RecentlyViewedProduct>(myContext, R.layout.home_list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(myContext)
        val view: View =  inflater.inflate(R.layout.home_list_item, null)
        val imageView: ImageView = view.findViewById(R.id.productImage)
        val name: TextView = view.findViewById(R.id.productName)
        val request: TextView = view.findViewById(R.id.request)
        val count: TextView =  view.findViewById(R.id.count)

        imageView.setImageResource(arrayList[position].imageId)
        name.text = arrayList[position].name
        request.text = arrayList[position].requestCount.toString()
        count.text = arrayList[position].voteCount.toString()

        return view
    }
}