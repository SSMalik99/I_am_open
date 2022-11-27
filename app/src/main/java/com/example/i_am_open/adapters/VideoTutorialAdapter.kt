package com.example.i_am_open.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.i_am_open.ProductDetailFragmentDirections
import com.example.i_am_open.R
import com.example.i_am_open.TutorialModel

class VideoTutorialAdapter(val context: Context, val videos : ArrayList<TutorialModel>)
    : RecyclerView.Adapter<VideoTutorialHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoTutorialHolder {
        return VideoTutorialHolder(
            LayoutInflater.from(context).inflate(
                R.layout.video_tutorial_item,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: VideoTutorialHolder, position: Int) {
       val video = videos[position]
        holder.tutorialTitle.text = video.title
//        holder.view

    }

    override fun getItemCount(): Int {
        return videos.size
    }

}


class VideoTutorialHolder(val view: View) :RecyclerView.ViewHolder(view) {
    var tutorialId = 0

    var tutorialTitle = view.findViewById<TextView>(R.id.videoTutorialTitle)!!
    var videoView = view.findViewById<VideoView>(R.id.videoTutorialView)!!

    init {
//        tutorialTitle.setOnClickListener{
//            view.findNavController().navigate(ProductDetailFragmentDirections.actionProductDetailFragmentToProductTutorialFragment2(tutorialId))
//        }
    }
}