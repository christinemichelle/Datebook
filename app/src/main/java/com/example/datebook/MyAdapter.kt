package com.example.datebook

import android.content.Context
import android.location.Geocoder.GeocodeListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter(private val context:Context, private val datalist:List<Dataclass>):RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent,false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.recTitle.text = datalist[position].dataTitle
        holder.recPriority.text = datalist[position].datapriority
        holder.recDesc.text = datalist[position].datadescription


    }

}

class MyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
    var recTitle:TextView
    var recDesc: TextView
    var recPriority: TextView
    var recCard:CardView
    var recImagae:ImageView

    init {
        recTitle= itemview.findViewById(R.id.recTitle)
        recDesc= itemview.findViewById(R.id.recDesc)
        recPriority= itemview.findViewById(R.id.recPriority)
        recCard = itemview.findViewById(R.id.recCard)
        recImagae= itemview.findViewById(R.id.recImage)
    }
}
