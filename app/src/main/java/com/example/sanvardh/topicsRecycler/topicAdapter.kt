package com.example.sanvardh.topicsRecycler

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sanvardh.R

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

        when (position) {
            0 -> Toast.makeText(context , "Physics" , Toast.LENGTH_SHORT).show()
            1 -> Toast.makeText(context , "Biology" , Toast.LENGTH_SHORT).show()
            2 -> Toast.makeText(context , "Mechanics" , Toast.LENGTH_SHORT).show()
            3 -> Toast.makeText(context , "Astronomy" , Toast.LENGTH_SHORT).show()
            4 -> Toast.makeText(context , "Architecture" , Toast.LENGTH_SHORT).show()
            5 -> Toast.makeText(context , "Chemistry" , Toast.LENGTH_SHORT).show()

        }

    }
}
}