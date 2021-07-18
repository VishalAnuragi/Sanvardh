package com.example.sanvardh.innerRecycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sanvardh.R
import com.example.sanvardh.ar_model.AR_Activity

class innerAdapter (
    private val context: Context,
    private val images: Array<innerModel>
        ) : RecyclerView.Adapter<innerAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img = itemView.findViewById<ImageView>(R.id.modelImage)
        val imgTitle = itemView.findViewById<TextView>(R.id.modelTitle)
        val card = itemView.findViewById<CardView>(R.id.cardModel)

        fun bindView(image: innerModel) {
            img.setImageResource(image.imageSrc)
            imgTitle.text = image.title
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): innerAdapter.ImageViewHolder =
        innerAdapter.ImageViewHolder( LayoutInflater.from(context).inflate(R.layout.item_model, parent, false))

    override fun onBindViewHolder(holder: innerAdapter.ImageViewHolder, position: Int) {
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

    override fun getItemCount(): Int = images.size
}