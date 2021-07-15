package com.example.sanvardh.topicsRecycler

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sanvardh.R
import com.example.sanvardh.ar_model.AR_Activity

class topicAdapter(
    private val context: Context,
    private val images: Array<topicModel>
) : RecyclerView.Adapter<topicAdapter.ImageViewHolder>() {


    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val img = itemView.findViewById<ImageView>(R.id.topicImage)
    val imgTitle = itemView.findViewById<TextView>(R.id.topicTitle)
    val card = itemView.findViewById<CardView>(R.id.card1)
    fun bindView(image: topicModel) {
        img.setImageResource(image.imageSrc)
        imgTitle.text = image.title
    }
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
    ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false))

override fun getItemCount(): Int = images.size

override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
    holder.bindView(images[position])
    holder.card.setOnClickListener {

        var topic : String ?= null

        when (position) {
            0 -> topic = "PHYSICS"
            1 -> topic = "BIOLOGY"
            2 -> topic = "MECHANICS"
            3 -> topic = "ASTRONOMY"
            4 -> topic = "ARCHITECTURE"
            5 -> topic = "CHEMISTRY"

        }

        val intent = Intent(context, AR_Activity::class.java).apply {
            putExtra("TOPIC", topic)
        }
        context.startActivity(intent)


    }
}
}