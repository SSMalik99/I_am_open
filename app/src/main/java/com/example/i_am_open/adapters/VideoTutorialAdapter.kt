package com.example.i_am_open.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.i_am_open.R
import com.example.i_am_open.TutorialModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

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
        holder.tutorialTitle.text = if (video.title != "N/A") video.title else "Video Tutorial"
//        holder.videoView.
        holder.videoView.enableAutomaticInitialization = true
        val lifecycle = holder.view.findViewTreeLifecycleOwner()?.lifecycle
        lifecycle?.addObserver(holder.videoView)

        holder.videoView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = (video.description).split("?v=")[1]//video.description
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })




    }

    override fun getItemCount(): Int {
        return videos.size
    }

}


class VideoTutorialHolder(val view: View) :RecyclerView.ViewHolder(view) {
    var tutorialId = 0

    var tutorialTitle = view.findViewById<TextView>(R.id.videoTutorialTitle)!!
    var videoView = view.findViewById<YouTubePlayerView>(R.id.videoTutorialWebView)!!

    init {
//        tutorialTitle.setOnClickListener{
//            view.findNavController().navigate(ProductDetailFragmentDirections.actionProductDetailFragmentToProductTutorialFragment2(tutorialId))
//        }
    }
}