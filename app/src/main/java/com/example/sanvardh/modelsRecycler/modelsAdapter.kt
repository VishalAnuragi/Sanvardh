package com.example.sanvardh.modelsRecycler

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sanvardh.R
import com.example.sanvardh.ar.AR_Activity
import com.example.sanvardh.modelsActivity.Models_Activity


class modelsAdapter (
    private val context: Context,
    private val images: Array<modelsModel>
        ): RecyclerView.Adapter<modelsAdapter.ImageViewHolder>() {




    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img = itemView.findViewById<ImageView>(R.id.modelImage)
        val imgTitle = itemView.findViewById<TextView>(R.id.modelTitle)
        val card = itemView.findViewById<CardView>(R.id.cardModel)

        fun bindView(modelsModel: modelsModel) {
            img.setImageResource(modelsModel.imageSrc)
            imgTitle.text = modelsModel.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
    ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_model, parent, false))

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(images[position])
        holder.card.setOnClickListener {



            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val topic = preferences.getString("TOPIC", "")

            var model : String ?= null

            when (topic) {
                "PHYSICS" ->  when (position) {
                    0 -> model = "CUBE"
                    1 -> model = "ANTIQUE CAMERA"
                    2 -> model = "BOOM BOX"
                    3 -> model = "SCIFI HELMET"

                }

                "BIOLOGY" ->   when (position) {
                    0 -> model = "FISH"
                    1 -> model = "FOX"
                    2 -> model = "AVACADO"
                }

                "MECHANICS" ->  when (position) {
                    0 -> model = "ENGINE"
                    1 -> model = "GEARBOX"
                    2 -> model = "SAW"
                    3 -> model = "TRUCK"
                    4 -> model = "ROBOT"
                }

                "ARCHITECTURE" ->  when (position) {
                    0 -> model = "SPONZA"
                    1 -> model = "VIRTUAL CITY"
                }

            }

           Log.i("TAG123" , "" +position + ":" + topic + ":" + model)

            val intent = Intent(context, AR_Activity::class.java).apply {
                putExtra("MODEL", model)
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = images.size


}