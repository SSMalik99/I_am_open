package com.example.i_am_open

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide

class CompanyAdapter(private val myContext: Context,
                     private val arrayList: ArrayList<CompanyModel>):
    ArrayAdapter<CompanyModel>(myContext, R.layout.company_list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(myContext)
        val view: View =  inflater.inflate(R.layout.company_list_item, null)
        val imageView: ImageView = view.findViewById(R.id.companyImage)

        val name: TextView = view.findViewById(R.id.companyName)
        val description: TextView = view.findViewById(R.id.companyDescription)

        Glide.with(myContext).load(arrayList[position].image).into(imageView)
        name.text = arrayList[position].name
        description.text = arrayList[position].description

        return view
    }
}