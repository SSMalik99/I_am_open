package com.example.i_am_open

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.i_am_open.adapters.VideoTutorialAdapter

class ProductVideoGuideFragment : Fragment() {

    private var productId : Int = 0
    lateinit var databaseHelper : DatabaseHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        productId = arguments?.getInt("productId")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_product_video_guide, container, false)
        databaseHelper = DatabaseHelper(view.context)

        //  Get videos from the database
        val videoTutorials = databaseHelper.productTutorial(productId, TutorialType.VIDEO)

        if (videoTutorials.size < 1) {
            view.findViewById<TextView>(R.id.blankVideoTutorial).visibility = View.VISIBLE
        }else {

            val recycleView = view.findViewById<RecyclerView>(R.id.videoTutorialRecycleView)
            val videoAdapter = VideoTutorialAdapter(view.context, videoTutorials)
            recycleView.layoutManager = LinearLayoutManager(view.context)
            recycleView.adapter = videoAdapter
        }
        return view
    }


}