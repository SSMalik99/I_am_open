package com.example.i_am_open.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.i_am_open.ProductDetailFragmentDirections
import com.example.i_am_open.R
import com.example.i_am_open.TutorialModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController

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

        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // We're using pre-made custom ui
                val defaultPlayerUiController =
                    DefaultPlayerUiController(holder.videoView, youTubePlayer)
                defaultPlayerUiController.showFullscreenButton(true)

                defaultPlayerUiController.setFullScreenButtonClickListener {
                    holder.videoView.enterFullScreen()
                }


                holder.videoView.setCustomPlayerUi(defaultPlayerUiController.rootView)

                val videoId = "gI4Pncpc06I"//video.description

                youTubePlayer.cueVideo(videoId, 0f)
            }
        }

//        val mc = MediaController(holder.view.context)
//        holder.videoView.setMediaController(mc)
//        val uri = Uri.parse(video.description)
//        holder.videoView.setVideoURI(uri)
//        holder.videoView.requestFocus()
//        holder.videoView.start()

//        val frameVideo = """<html><body><iframe width="420" height="315" src="${Uri.parse(video.description)}" frameborder="0" allowfullscreen></iframe></body></html>""";
//        holder.videoView.webViewClient = WebViewClient()
//        val webSettings = holder.videoView.settings
//        webSettings.setJavaScriptEnabled(true);
//        holder.videoView.loadData(frameVideo, "text/html", "utf-8");


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