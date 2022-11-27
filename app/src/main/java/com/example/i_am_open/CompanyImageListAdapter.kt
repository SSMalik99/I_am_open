package com.example.i_am_open

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CompanyImageListAdapter(
    private val companyList: ArrayList<CompanyModel>):
    RecyclerView.Adapter<CompanyImageListAdapter.ViewHolder>() {

    var myContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.company_image_list, parent, false)
        myContext = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.companyImageView.setImageURI(companyList[position].image)
        myContext?.let { Glide.with(it).load(companyList[position].image).into(holder.companyImageView) }
    }

    override fun getItemCount(): Int {
        return companyList.size
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val companyImageView: ImageView = view.findViewById(R.id.companyImage)
    }
}
