package com.example.i_am_open

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CompanyAdapter(private val myContext: Context,
                     private val arrayList: ArrayList<Company>):
    ArrayAdapter<Company>(myContext, R.layout.company_list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(myContext)
        val view: View =  inflater.inflate(R.layout.company_list_item, null)
        val imageView: LinearLayout = view.findViewById(R.id.companyImage)
        val name: TextView = view.findViewById(R.id.companyName)
        val description: TextView = view.findViewById(R.id.companyDescription)

        imageView.setBackgroundResource(arrayList[position].imageId)
        name.text = arrayList[position].name
        description.text = arrayList[position].description

        return view
    }
}