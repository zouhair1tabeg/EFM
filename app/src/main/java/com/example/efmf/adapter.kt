package com.example.efmf

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class adapter(
    private val context: Context,
    private val smartwatch: List<Smartwatch>,
): ArrayAdapter<Smartwatch>(context,0, smartwatch){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView?:LayoutInflater.from(context).inflate(R.layout.item, parent,false)

        val watch = getItem(position)

        val name = view.findViewById<TextView>(R.id.nom)
        val price = view.findViewById<TextView>(R.id.prix)
        val image = view.findViewById<ImageView>(R.id.img)

        watch?.let {
            name.text = it.name
            price.text = "${it.price} Dh"
            Glide.with(context)
                .load(it.imageURL)
                .into(image)
        }

        return view
    }
}